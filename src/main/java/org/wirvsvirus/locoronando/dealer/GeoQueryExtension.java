package org.wirvsvirus.locoronando.dealer;


import org.locationtech.jts.geom.Point;

public interface GeoQueryExtension<T> {
  Iterable<T> findbyLocation(Point point);
}
