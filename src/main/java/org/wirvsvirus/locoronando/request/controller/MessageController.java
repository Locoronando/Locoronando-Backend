package org.wirvsvirus.locoronando.request.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.wirvsvirus.locoronando.request.entity.Message;
import org.wirvsvirus.locoronando.request.repository.MessageRepository;

@Controller
public class MessageController {

  @Autowired
  private MessageRepository repository;

  // TODO: Error handling

  @MessageMapping("/send/customer")
  public void handleCustomerRequest(@Payload Message message) {
    message.setTimeStamp(System.currentTimeMillis());

    message = repository.save(message);

    System.out.println(message);
  }
}