package org.wirvsvirus.locoronando.category;

import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.wirvsvirus.locoronando.product.Product;

@Entity
public class Category {
  @Id
  @GeneratedValue
  private int id;
  private String name;
  private String description;
  @OneToMany(
    cascade = CascadeType.ALL
  )
  private List<Product> products;

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

  public List<Product> products() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
