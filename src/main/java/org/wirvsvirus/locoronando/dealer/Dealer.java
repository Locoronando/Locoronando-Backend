package org.wirvsvirus.locoronando.dealer;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Dealer {
  @Id
  @GeneratedValue
  private long id;
  private String name;
  private String category;

  @Embedded
  private Address address;
}
