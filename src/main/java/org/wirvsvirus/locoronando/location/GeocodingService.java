package org.wirvsvirus.locoronando.location;

import java.util.Optional;

import org.wirvsvirus.locoronando.dealer.Address;

public interface GeocodingService {

	/**
	 * Gets geo coordinates from a zipcode.
	 *
	 * @param zipcode The 5 digit German zipcode. Must be String as ZIP codes can
	 *                have leading 0 in Germany.
	 * @return geo coordinates
	 */
	Optional<Geocode> getGeocode(String zipcode);

	/**
	 * Gets geo coordinates from an {@link Address}.
	 *
	 * @param address {@link Address} of the geocoordinates
	 * @return geo coordinates
	 */
	Optional<Geocode> getGeocode(Address address);

}
