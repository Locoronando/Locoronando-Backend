package org.wirvsvirus.locoronando.location;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.wirvsvirus.locoronando.dealer.Address;

@Service
public class GeocodingServiceImpl implements GeocodingService {

	@Value("${geocode.zipcodesearch}")
	private String zipcodeSearchString;
	@Value("${geocode.addresssearch}")
	private String addressSearchString;
	@Value("${geocode.apikey}")
	private String apikey;
	@Value("${geocode.url}")
	private String url;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Gets geo coordinates from a description.
	 *
	 * @param description Free text description of a place.
	 * @return geo coordinates
	 */
	private Optional<Geocode> getGeocodeFromDescription(String description) {
		OpenCageDataResponse response = this.restTemplate.getForObject(this.url, OpenCageDataResponse.class,
				this.apikey, description);
		if ((response.getResults() == null) || response.getResults().isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(response.getResults().get(0).getGeometry());
	}

	@Override
	public Optional<Geocode> findGeocode(String zipcode) {
		return this.getGeocodeFromDescription(String.format(this.zipcodeSearchString, zipcode));
	}

	@Override
	public Optional<Geocode> findGeocode(Address address) {
		String formattedAddress = this.formatAddress(address);
		return this.getGeocodeFromDescription(formattedAddress);
	}

	private String formatAddress(Address address) {
		// TODO to implement
		// String.format(addressSearchString, address.getStreet(),
		// address.getHouseNumber(),
		// address.getPostCode());
		return address.toString();
	}

}
