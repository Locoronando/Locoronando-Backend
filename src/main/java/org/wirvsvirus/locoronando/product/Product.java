package org.wirvsvirus.locoronando.product;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.wirvsvirus.locoronando.dealer.model.db.Dealer;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Product {
  @Id
  @GeneratedValue
  private int id;
  private String title;
  private String price;
  private String description;
  private long createdAt;

  @ManyToOne
  private Dealer dealer;
}
