package org.wirvsvirus.locoronando.dealer;

import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.wirvsvirus.locoronando.dealer.model.db.Dealer;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class GeoQueryExtensionImpl implements GeoQueryExtension<Dealer> {
  private final EntityManager entityManager;

  @Override
  public Iterable<Dealer> findbyLocation(Point location) {
    return entityManager.createQuery(
      "select d " +
        "from Dealer d " +
        "where within(:location, d.area) = true", Dealer.class)
      .setParameter("location", location)
      .getResultList();
  }
}
