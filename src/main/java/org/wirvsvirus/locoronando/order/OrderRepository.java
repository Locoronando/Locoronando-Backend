package org.wirvsvirus.locoronando.order;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

  List<Order> findAllByCustomerId(long customerId);

  List<Order> findAllByDealerId(long dealerId);
}