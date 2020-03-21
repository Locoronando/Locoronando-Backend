package org.wirvsvirus.locoronando.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
  @Id
  @GeneratedValue
  private int id;
  private String title;
  private String price;
  private String description;
  private int handlerId;
  private long createdAt;

  public int id() {
    return id;
  }

  public String title() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String price() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String description() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int handlerId() {
    return handlerId;
  }

  public void setHandlerId(int handlerId) {
    this.handlerId = handlerId;
  }

  public long createdAt() {
    return createdAt;
  }
}
