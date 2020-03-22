package org.wirvsvirus.locoronando.customer;

import org.springframework.data.repository.CrudRepository;
import org.wirvsvirus.locoronando.customer.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}