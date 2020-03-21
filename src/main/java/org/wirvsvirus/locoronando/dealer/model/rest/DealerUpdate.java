package org.wirvsvirus.locoronando.dealer.model.rest;

import lombok.Data;
import org.wirvsvirus.locoronando.dealer.model.db.Address;

@Data
public class DealerUpdate {
  private String name;
  private String category;
  private short radius;
  private Address address;
}

