package org.wirvsvirus.locoronando.customer;

import org.springframework.data.repository.CrudRepository;
import org.wirvsvirus.locoronando.customer.model.Customer;
import org.wirvsvirus.locoronando.dealer.Dealer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

  /**
   * This method gets the current customer by the email.
   * (This needs to be hashed!)
   *
   * @param email    The email the customer signed up with
   * @return the Customer Object with this email
   */
  Customer findByEmail(String email);

  Customer findByName(String name);
}