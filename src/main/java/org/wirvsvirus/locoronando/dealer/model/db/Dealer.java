package org.wirvsvirus.locoronando.dealer.model.db;

import lombok.Data;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Dealer {
  @Id
  @GeneratedValue
  private long id;
  private String name;
  private String category;
  private short radius;

  private Geometry area;
  private Geometry point;

  @Embedded
  private Address address;
}
