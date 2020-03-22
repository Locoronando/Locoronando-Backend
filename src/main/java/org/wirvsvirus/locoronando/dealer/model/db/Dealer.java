package org.wirvsvirus.locoronando.dealer.model.db;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.wirvsvirus.locoronando.category.Category;
import org.wirvsvirus.locoronando.product.Product;

@Entity
@Data
public class Dealer {
  @Id
  @GeneratedValue
  private long id;
  private String name;
  private String category;
  private short radius;
  @OneToMany(
    cascade = CascadeType.ALL, fetch = FetchType.LAZY
  )
  private List<Category> categories;

  @JsonIgnore
  private Geometry area;
  private Geometry point;

  @Embedded
  private Address address;

  @OneToMany(mappedBy = "dealer", fetch = FetchType.LAZY)
  private List<Product> products;
  
  

}
