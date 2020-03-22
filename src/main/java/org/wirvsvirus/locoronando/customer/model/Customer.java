package org.wirvsvirus.locoronando.customer.model;

import lombok.Data;
import org.wirvsvirus.locoronando.customer.Role;

import javax.persistence.*;
import java.util.Set;
import org.wirvsvirus.locoronando.dealer.Address;

@Entity
@Data
@Table(name = "customer")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private int userId;
  private String name;
  private String email;
  private String password;

  @ManyToMany
  private Set<Role> roles;

  @Embedded
  private Address address;

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public int getUserId() {
    return userId;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  //TODO maybe preferred payment method?
}
