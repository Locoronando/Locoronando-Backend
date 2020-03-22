package org.wirvsvirus.locoronando.order;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // order is sql keyword
public class Order {

  @Id
  @GeneratedValue
  private long id;

  private long customerId;
  private long dealerId;

  @OneToMany
  @Cascade(CascadeType.ALL)
  private List<OrderItem> shoppingCart;

  @Enumerated(value = EnumType.STRING)
  private OrderStatus status;

  public Order() {

  }

  public Order(long customerId, long dealerId) {
    this.customerId = customerId;
    this.dealerId = dealerId;

    this.shoppingCart = new ArrayList<>();
    this.status = OrderStatus.OPEN;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public void addItem(OrderItem item) {
    shoppingCart.add(item);
  }

  public long getId() {
    return id;
  }

  public long getCustomerId() {
    return customerId;
  }

  public long getDealerId() {
    return dealerId;
  }

  public List<OrderItem> getShoppingCart() {
    return shoppingCart;
  }

  public OrderStatus getStatus() {
    return status;
  }
}