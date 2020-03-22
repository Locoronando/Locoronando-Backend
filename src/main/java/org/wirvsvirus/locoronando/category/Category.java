package org.wirvsvirus.locoronando.category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Category {
  @Id
  @GeneratedValue
  private int id;
  private String name;
  private String description;
  private int dealerId;

  public int id() {
    return id;
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

  public int dealerId() {
    return dealerId;
  }
}
