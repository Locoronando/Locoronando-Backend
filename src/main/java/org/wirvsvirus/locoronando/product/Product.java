package org.wirvsvirus.locoronando.product;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
  @Id
  private UUID uniqueId;
  private String title;
  private String price;
  private String description;
  private int handlerId;
  private int categoryId;
  private long createdAt;

  public UUID uniqueId() {
    return uniqueId;
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

  public int categoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public long createdAt() {
    return createdAt;
  }
}
