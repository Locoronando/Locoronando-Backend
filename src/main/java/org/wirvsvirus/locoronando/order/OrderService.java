package org.wirvsvirus.locoronando.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class OrderService {

  private final OrderRepository repository;

  @Autowired
  public OrderService(OrderRepository repository) {
    this.repository = repository;
  }

  public Order create(long customerId, long dealerId) {
    Order order = new Order(customerId, dealerId);

    return repository.save(order);
  }

  public Order addItem(long orderId, OrderItem item) {
    Order order = repository.findById(orderId).orElseThrow(IllegalArgumentException::new);

    order.addItem(item);

    return repository.save(order);
  }

  public Order changeStatus(long orderId, OrderStatus status) {
    Order order = repository.findById(orderId).orElseThrow(IllegalArgumentException::new);

    order.setStatus(status);

    return repository.save(order);
  }

  public Order find(long orderId) {
    return repository.findById(orderId).orElseThrow(IllegalArgumentException::new);
  }

  public List<Order> findAllByCustomer(long customerId) {
    return repository.findAllByCustomerId(customerId);
  }

  public List<Order> findAllByDealer(long dealerId) {
    return repository.findAllByDealerId(dealerId);
  }
}