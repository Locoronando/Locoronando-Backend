package org.wirvsvirus.locoronando.product;

import lombok.Data;
import org.wirvsvirus.locoronando.dealer.model.db.Dealer;

import javax.persistence.CascadeType;
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

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinColumn(name = "dealer_id", nullable = false)
  private Dealer dealer;
  
  @Override
  public String toString() {
	  return "Product; Wenn man hier den Autogenerated Stuff lässt, fliegt der Stack durch die Luft, weil sich Product und Dealer rekursiv aufrufen..."; //TODO
  }
}
