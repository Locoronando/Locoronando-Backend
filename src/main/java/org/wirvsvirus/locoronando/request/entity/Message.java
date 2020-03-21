package org.wirvsvirus.locoronando.request.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {

  @Id
  @GeneratedValue
  private long id;

  @JsonProperty("message")
  private String message;

  private long customerId;
  private long dealerId;

  @JsonProperty("sentFrom")
  private SentType sentType;

  private long timeStamp;

  @JsonProperty("type")
  private RequestType type;

  @JsonProperty("productId")
  private long productId;

  public Message() {

  }

  public void setTimeStamp(long timeStamp) {
    this.timeStamp = timeStamp;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  public void setDealerId(long dealerId) {
    this.dealerId = dealerId;
  }

  public void setSentType(SentType sentType) {
    this.sentType = sentType;
  }

  @Override
  public String toString() {
    return "Message{" +
      "id=" + id +
      ", message='" + message + '\'' +
      ", customerId=" + customerId +
      ", dealerId=" + dealerId +
      ", timeStamp=" + timeStamp +
      ", type=" + type +
      ", productId=" + productId +
      '}';
  }
}