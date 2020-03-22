package org.wirvsvirus.locoronando.dealer;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Dealer {
  @Id
  @GeneratedValue
  private int id;

  private String ownerName;
  private String shopName;
  private String email;
  private String password;

  @Embedded
  private Address address;

  public int id() {
    return id;
  }

  public String ownerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Address address() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

}
