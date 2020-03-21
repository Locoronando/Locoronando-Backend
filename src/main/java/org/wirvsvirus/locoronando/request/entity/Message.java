package org.wirvsvirus.locoronando.request.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Class description.
 *
 * @author Paul2708
 */
@Entity
public class Message {

  @Id
  @GeneratedValue
  private long id;

  // TODO: Use class annotation for json

  @JsonProperty("message")
  private String message;

  @JsonProperty("customerId")
  private long customerId;

  @JsonProperty("dealerId")
  private long dealerId;

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