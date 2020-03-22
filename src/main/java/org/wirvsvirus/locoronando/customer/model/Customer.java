package org.wirvsvirus.locoronando.customer.model;

import lombok.Data;
import org.wirvsvirus.locoronando.dealer.model.db.Address;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Customer {
  @Id
  @GeneratedValue
  private long id;
  private String name;
  @Embedded
  private Address address;

  //TODO maybe preferred payment method?
}
