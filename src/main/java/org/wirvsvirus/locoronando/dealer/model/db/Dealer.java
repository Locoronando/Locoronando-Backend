package org.wirvsvirus.locoronando.dealer.model.db;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.wirvsvirus.locoronando.category.Category;

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
    cascade = CascadeType.ALL
  )
  private List<Category> categories;

  private Geometry area;
  private Geometry point;

  @Embedded
  private Address address;

}
