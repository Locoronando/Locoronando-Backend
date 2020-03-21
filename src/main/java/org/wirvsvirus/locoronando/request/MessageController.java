package org.wirvsvirus.locoronando.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class MessageController {

  private static final String MESSAGE_ENDPOINT = "/queue/message";

  Logger logger = LoggerFactory.getLogger(MessageController.class);

  @Autowired
  private MessageRepository repository;

  @Autowired
  private SocketAssignment assignment;

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  // TODO: Error handling
  // TODO: Security?

  @MessageMapping("/send/{targetId}")
  public void handleCustomerRequest(@Payload Message message,
                                    @Header("simpSessionId") String sessionId,
                                    @DestinationVariable long targetId) {
    Optional<User> optional = assignment.findUserBySession(sessionId);
    if (!optional.isPresent()) {
      logger.error("Couldn't find user with sessionId " + sessionId);
      return;
    }

    // Set message attributes
    User user = optional.get();
    switch (user.getParticipant()) {
      case CUSTOMER:
        message.setCustomerId(user.getId());
        message.setDealerId(targetId);
        break;
      case DEALER:
        message.setCustomerId(targetId);
        message.setDealerId(user.getId());
        break;
    }

    message.setTimeStamp(System.currentTimeMillis());
    message.setSender(user.getParticipant());

    // Send message
    User target = new User(user.getParticipant() == Participant.CUSTOMER ? Participant.DEALER : Participant.CUSTOMER, targetId);
    Optional<String> sessionOptional = assignment.findSessionByUser(target);

    if (!sessionOptional.isPresent()) {
      logger.error("Couldn't find a sessionId to " + target + ". Maybe offline?");
      return;
    }

    sendMessage(sessionOptional.get(), repository.save(message));
  }

  /**
   * This method sends all messages for one specific user. This user is the owner of the current session
   *
   * @param sessionId the current user's session ID we get the Participant from for clearer identification
   */
  @MessageMapping("/receive")
  public void receiveAllMessages(@Header("simpSessionId") String sessionId) {
    //Get user from sessionId
    User user = assignment.findUserBySession(sessionId).orElseThrow(NullPointerException::new);
    //Get ALL messages
    boolean isCustomer = user.getParticipant() == Participant.CUSTOMER;
    List<Message> messages = isCustomer ? repository.findAllByCustomerId(user.getId()) : repository.findAllByDealerId(user.getId());
    //Sort and send messages for each chat
    List<Long> partnerIds = messages.stream()
      .map(message -> isCustomer ? message.getDealerId() : message.getCustomerId())
      .distinct()
      .collect(Collectors.toList());
    List<List<Message>> sortedMessages = new ArrayList<>();
    partnerIds.forEach(partnerId -> sortedMessages.add(messages.stream()
      .filter(message -> isCustomer ? message.getDealerId() == partnerId : message.getCustomerId() == partnerId)
      .collect(Collectors.toList())));
    //Send this list
    this.sendAllMessages(sessionId, sortedMessages);
  }

  /**
   * Sends all messages from one chat the user is currently in
   *
   * @param customerId The customer Id of the current chat
   * @param dealerId   The dealer Id of the current chat
   */
  @MessageMapping("/receive/{customerId}/{dealerId}")
  public void receiveAllMessages(@DestinationVariable long customerId, @DestinationVariable long dealerId, @Header("simpSessionId") String sessionId) {
    this.sendAllMessagesFromChat(sessionId, repository.findAllByCustomerIdAndDealerId(customerId, dealerId));
  }

  @MessageMapping("/register")
  public void register(@Payload User user, @Header("simpSessionId") String sessionId) {
    // TODO: Verify registration

    assignment.assign(sessionId, user);

    logger.info("Assigned " + sessionId + " to " + user);
  }

  /**
   * Send all messages that this user participated in
   *
   * @param sessionId destination sessionId
   * @param messages  the messages that will be sent
   */
  private void sendAllMessages(String sessionId, List<List<Message>> messages) {
    this.send(sessionId, "/queue/message/all", messages);
  }

  /**
   * Send a message from a specific chat
   *
   * @param sessionId destination sessionId
   * @param messages  the message from the chat that will be sent
   */
  private void sendAllMessagesFromChat(String sessionId, List<Message> messages) {
    this.send(sessionId, "/queue/message/chat", messages);
  }

  /**
   * Send one specific message
   *
   * @param sessionId destination sessionId
   * @param message   the message to be sent
   */
  private void sendMessage(String sessionId, Message message) {
    this.send(sessionId, "/queue/message/new", message);
  }

  /**
   * Basic send method to send a specific object through a channel to a session
   *
   * @param sessionId the destination the obj will be sent to
   * @param channel   the endpoint to send it through
   * @param obj       the obj to be sent
   */
  private void send(String sessionId, String channel, Object obj) {
    SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor
      .create(SimpMessageType.MESSAGE);
    headerAccessor.setSessionId(sessionId);
    headerAccessor.setLeaveMutable(true);

    messagingTemplate.convertAndSendToUser(sessionId, channel, obj,
      headerAccessor.getMessageHeaders());
  }
}