package org.wirvsvirus.locoronando.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

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
    // TODO: Verify ids
    message.setTimeStamp(System.currentTimeMillis());
    message.setCustomerId(customerId);
    message.setDealerId(dealerId);
    message.setSender(Participant.CUSTOMER);

    return repository.save(message);
  }

  @MessageMapping("/send/dealer/{dealerId}/{customerId}")
  @SendTo("/queue/customer/{customerId}")
  public Message handleDealerRequest(@Payload Message message,
                                     @DestinationVariable long customerId,
                                     @DestinationVariable long dealerId) {
    // TODO: Verify ids
    message.setTimeStamp(System.currentTimeMillis());
    message.setCustomerId(customerId);
    message.setDealerId(dealerId);
    message.setSender(Participant.DEALER);

    return repository.save(message);
  }
}