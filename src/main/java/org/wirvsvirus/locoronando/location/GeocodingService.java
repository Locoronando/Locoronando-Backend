package org.wirvsvirus.locoronando.location;

import java.util.Optional;

import org.wirvsvirus.locoronando.dealer.model.db.Address;

public interface GeocodingService {

	/**
	 * Searches for geo coordinates from a zipcode.
	 *
	 * @param zipcode The 5 digit German zipcode. Must be String as ZIP codes can
	 *                have leading 0 in Germany.
	 * @return geo coordinates if found
	 */
	Optional<Geocode> findGeocode(String zipcode);

	/**
	 * Searches for geo coordinates from an {@link Address}.
	 *
	 * @param address {@link Address} of the geocoordinates
	 * @return geo coordinates if found
	 */
	Optional<Geocode> findGeocode(Address address);

}
