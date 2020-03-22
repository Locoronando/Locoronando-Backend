package org.wirvsvirus.locoronando.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public final class OrderController {

  private final OrderService service;

  @Autowired
  public OrderController(OrderService service) {
    this.service = service;
  }

  @PostMapping("/create/{customerId}/{dealerId}")
  public Order create(@PathVariable long customerId, @PathVariable long dealerId) {
    return service.create(customerId, dealerId);
  }

  @PutMapping("/{orderId}/items/add/")
  public Order addItem(@PathVariable long orderId, @RequestBody OrderItem item) {
    return service.addItem(orderId, item);
  }

  @PatchMapping("/{orderId}/status/")
  public Order changeStatus(@PathVariable long orderId, @RequestBody OrderStatusHolder holder) {
    return service.changeStatus(orderId, holder.getStatus());
  }

  @GetMapping("/{orderId}/")
  private Order get(@PathVariable long orderId) {
    return service.find(orderId);
  }

  @GetMapping("/customer/{customerId}")
  public List<Order> getCustomerOrders(@PathVariable long customerId) {
    return service.findAllByCustomer(customerId);
  }

  @GetMapping("/dealer/{dealerId}")
  public List<Order> getDealerOrders(@PathVariable long dealerId) {
    return service.findAllByDealer(dealerId);
  }
}