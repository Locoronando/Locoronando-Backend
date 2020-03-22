package org.wirvsvirus.locoronando.accountmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.wirvsvirus.locoronando.customer.Role;
import org.wirvsvirus.locoronando.dealer.Address;
import org.wirvsvirus.locoronando.request.Participant;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @JsonProperty("username")
  private String username;

  @JsonProperty("email")
  private String email;

  @JsonProperty("password")
  private String password;

  @JsonProperty("type")
  private Participant type;

  @ManyToMany
  @JsonIgnore
  private Set<Role> roles;

  @Embedded
  private Address address;

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public Participant getType() {
    return type;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public Address getAddress() {
    return address;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setType(Participant type) {
    this.type = type;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  //TODO maybe preferred payment method?
}
