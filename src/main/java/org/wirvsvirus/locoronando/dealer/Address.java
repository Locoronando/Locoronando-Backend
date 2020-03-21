package org.wirvsvirus.locoronando.dealer;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {
  private String street;
  private String houseNumber;
  private int postalCode;
}
