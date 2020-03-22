package org.wirvsvirus.locoronando.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Product {
  @Id
  @GeneratedValue
  private int id;
  private String title;
  private String price;
  private String description;
  private int dealerId;
  private int categoryId;
  private long createdAt;
}
