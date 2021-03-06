package org.wirvsvirus.locoronando.request;

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

  private Participant sender;

  private long timeStamp;

  @JsonProperty("type")
  private RequestType type;

  @JsonProperty("productId")
  private long productId;

  public void setTimeStamp(long timeStamp) {
    this.timeStamp = timeStamp;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  public void setDealerId(long dealerId) {
    this.dealerId = dealerId;
  }

  public void setSender(Participant sender) {
    this.sender = sender;
  }

  public long getCustomerId() {
    return customerId;
  }

  public long getDealerId() {
    return dealerId;
  }

  public long getId() {
    return id;
  }

  public String getMessage() {
    return message;
  }

  public Participant getSender() {
    return sender;
  }

  public long getTimeStamp() {
    return timeStamp;
  }

  public RequestType getType() {
    return type;
  }

  public long getProductId() {
    return productId;
  }

  @Override
  public String toString() {
    return "Message{" +
      "id=" + id +
      ", message='" + message + '\'' +
      ", customerId=" + customerId +
      ", dealerId=" + dealerId +
      ", sender=" + sender +
      ", timeStamp=" + timeStamp +
      ", type=" + type +
      ", productId=" + productId +
      '}';
  }
}