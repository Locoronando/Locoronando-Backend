package org.wirvsvirus.locoronando.category;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
  @Id
  private UUID uniqueId;
  private String name;
  private String description;
  private int handlerId;

  public UUID uniqueId() {
    return uniqueId;
  }

  public String name() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String description() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int handlerId() {
    return handlerId;
  }

  public void setHandlerId(int handlerId) {
    this.handlerId = handlerId;
  }
}
