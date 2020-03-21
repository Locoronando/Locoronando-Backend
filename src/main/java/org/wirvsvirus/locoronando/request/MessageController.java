package org.wirvsvirus.locoronando.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.crypto.MacSpi;
import java.util.List;
import java.util.Optional;

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
    User target =  new User(user.getParticipant() == Participant.CUSTOMER ? Participant.DEALER : Participant.CUSTOMER, targetId);
    Optional<String> sessionOptional = assignment.findSessionByUser(target);

    if (!sessionOptional.isPresent()) {
      logger.error("Couldn't find a sessionId to " + target + ". Maybe offline?");
      return;
    }

    sendMessage(sessionOptional.get(), repository.save(message));
  }

  @MessageMapping("/receive/{customerId}/{dealerId}")
  @SendTo("/queue/customer/{customerId}")
  public List<Message> receiveAllMessages(@DestinationVariable long customerId, @DestinationVariable long dealerId) {
    // TODO: Verify ids
    return repository.findAllByCustomerIdAndDealerId(customerId, dealerId);
  }

  @MessageMapping("/register")
  public void register(@Payload User user, @Header("simpSessionId") String sessionId) {
    // TODO: Verify registration

    assignment.assign(sessionId, user);

    sendMessage(sessionId, new Message());

    logger.info("Assigned " + sessionId + " to " + user);
  }

  private void sendMessage(String sessionId, Message message) {
    SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
    headerAccessor.setSessionId(sessionId);
    headerAccessor.setLeaveMutable(true);

    messagingTemplate.convertAndSendToUser(headerAccessor.getUser().getName(),
      "/queue/message", message, headerAccessor.getMessageHeaders());
  }
}