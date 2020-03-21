package org.wirvsvirus.locoronando.dealer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.util.GeometricShapeFactory;
import org.springframework.stereotype.Service;
import org.wirvsvirus.locoronando.dealer.model.db.Dealer;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealerService {
  private final DealerRepository dealerRepository;

  public void create(Dealer dealer) {
    // TODO: replace by call to location service with address data
    double latitude = 48.78232d;
    double longitude = 9.17702;

    dealer.setArea(calculateArea(latitude, longitude, dealer.getRadius()));
    dealerRepository.save(dealer);
  }

  public Iterable<Dealer> findAll() {
    return dealerRepository.findAll();
  }

  public Iterable<Dealer> findByLocation(String plz) {
    // TODO: replace by call to location service with plz resolution?
    double latitude = 48.78232d;
    double longitude = 9.17702;

    GeometryFactory gf = new GeometryFactory();
    Point location = gf.createPoint(new Coordinate(latitude, longitude));

    return dealerRepository.findbyLocation(location);
  }

  private Polygon calculateArea(double latitude, double longitude, short radiusInKm) {
    double diameterInMeters = radiusInKm * 1000d;

    GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
    shapeFactory.setNumPoints(24); // adjustable
    shapeFactory.setCentre(new Coordinate(latitude, longitude));

    // Length in meters of 1° of latitude = always 111.32 km
    shapeFactory.setHeight(diameterInMeters/111320d);

    // Length in meters of 1° of longitude = 40075 km * cos( latitude ) / 360
    shapeFactory.setWidth(diameterInMeters / (40075000 * Math.cos(Math.toRadians(latitude)) / 360));

    Polygon area = shapeFactory.createEllipse();
    return area;
  }


}
