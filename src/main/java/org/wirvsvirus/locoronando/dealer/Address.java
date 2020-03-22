package org.wirvsvirus.locoronando.dealer;

import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {
  private String street;
  private String houseNumber;
  private String postalCode;
  private String city;

  //The framework requires empty constructor
  public Address() {
  }

  private Address(
    String street,
    String houseNumber,
    String postalCode,
    String city
  ) {
    this.street = street;
    this.houseNumber = houseNumber;
    this.postalCode = postalCode;
    this.city = city;
  }

  public String street() {
    return street;
  }

  public String houseNumber() {
    return houseNumber;
  }

  public String postalCode() {
    return postalCode;
  }

  public String city() {
    return city;
  }

  public static Address create(
    String street,
    String houseNumber,
    String postalCode,
    String city) {
    return new Address(
      street,
      houseNumber,
      postalCode,
      city);
  }
}
