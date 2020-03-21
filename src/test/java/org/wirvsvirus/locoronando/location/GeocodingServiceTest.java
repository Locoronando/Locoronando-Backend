package org.wirvsvirus.locoronando.location;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wirvsvirus.locoronando.dealer.model.db.Address;

@SpringBootTest
public class GeocodingServiceTest {

	@Autowired
	private GeocodingService geocodingService;

	@Test
	void testGetGeocodeFromZipcode() {
		// given
		String zipcode = "74348";

		// when
		Optional<Geocode> geocode = this.geocodingService.findGeocode(zipcode);

		// then
		assertThat(geocode.get().getLat()).isEqualTo(49.0769491);
		assertThat(geocode.get().getLng()).isEqualTo(9.1512248);
	}

	@Test
	void testGetGeocodeFromAddress() {
		// given
		Address address = new Address();
		address.setStreet("Willy-Brandt-Straße");
		address.setHouseNumber("1");
		address.setPostalCode("10557");

		// when
		Optional<Geocode> geocode = this.geocodingService.findGeocode(address);

		// then
		assertThat(geocode.get().getLat()).isEqualTo(52.5200453);
		assertThat(geocode.get().getLng()).isEqualTo(13.3691337);
	}
}
