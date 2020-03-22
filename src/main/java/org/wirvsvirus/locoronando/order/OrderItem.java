package org.wirvsvirus.locoronando.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderItem {

  @Id
  @GeneratedValue
  private long id;

  private long productId;
  private int amount;

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public long getProductId() {
    return productId;
  }

  public int getAmount() {
    return amount;
  }
}