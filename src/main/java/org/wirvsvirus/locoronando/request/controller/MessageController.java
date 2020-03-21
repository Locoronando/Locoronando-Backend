package org.wirvsvirus.locoronando.request.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.wirvsvirus.locoronando.request.entity.Message;
import org.wirvsvirus.locoronando.request.entity.SentType;
import org.wirvsvirus.locoronando.request.repository.MessageRepository;

@Controller
public class MessageController {

  @Autowired
  private MessageRepository repository;

  // TODO: Error handling
  // TODO: Security?

  @MessageMapping("/send/customer/{customerId}/{dealerId}")
  @SendTo("/queue/dealer/{dealerId}")
  public Message handleCustomerRequest(@Payload Message message,
                                       @DestinationVariable long customerId,
                                       @DestinationVariable long dealerId) {
    message.setTimeStamp(System.currentTimeMillis());
    message.setCustomerId(customerId);
    message.setDealerId(dealerId);
    message.setSentType(SentType.CUSTOMER);

    return repository.save(message);
  }

  @MessageMapping("/send/dealer/{dealerId}/{customerId}")
  @SendTo("/queue/customer/{customerId}")
  public Message handleDealerRequest(@Payload Message message,
                                     @DestinationVariable long customerId,
                                     @DestinationVariable long dealerId) {
    message.setTimeStamp(System.currentTimeMillis());
    message.setCustomerId(customerId);
    message.setDealerId(dealerId);
    message.setSentType(SentType.DEALER);

    return repository.save(message);
  }
}