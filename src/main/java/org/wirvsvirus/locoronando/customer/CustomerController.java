package org.wirvsvirus.locoronando.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;

/**
 * This class represents the customer controller keep track of the Requests coming in from
 * the frontend.
 */
public class CustomerController {

  @Autowired
  private CustomerRepository customerRepository;

}
