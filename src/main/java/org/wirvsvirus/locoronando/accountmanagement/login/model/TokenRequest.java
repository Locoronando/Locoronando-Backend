package org.wirvsvirus.locoronando.accountmanagement.login.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.wirvsvirus.locoronando.request.Participant;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TokenRequest {

  @Id
  @JsonProperty("username")
  private String username;

  @JsonProperty("password")
  private String password;

  @JsonProperty("type")
  private Participant type;

  public TokenRequest() {
  }

  public void setType(Participant type) {
    this.type = type;
  }

  public void setUsername(String username) {
    System.out.println("Setting username!");
    this.username = username;
  }

  public void setPassword(String password) {
    System.out.println("Setting password");
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public Participant getType() {
    return type;
  }
}
