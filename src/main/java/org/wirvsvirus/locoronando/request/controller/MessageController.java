package org.wirvsvirus.locoronando.request.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.wirvsvirus.locoronando.request.entity.Message;
import org.wirvsvirus.locoronando.request.repository.MessageRepository;

@Controller
public class MessageController {

  @Autowired
  private MessageRepository repository;

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  // TODO: Error handling

  @MessageMapping("/send/customer/{customerId}")
  @SendTo("/queue/dealer")
  public Message handleCustomerRequest(@Payload Message message, @DestinationVariable long customerId) {
    message.setTimeStamp(System.currentTimeMillis());

    message = repository.save(message);

    System.out.println("Saved message: " + message);
    System.out.println("Customer id: " + customerId);

    return message;
  }
}