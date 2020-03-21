package org.wirvsvirus.locoronando.location;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GeocodingServiceTest {

	@Autowired
	private GeocodingService geocodingService;

	@Test
	void testGetGeocodeFromDescription() {
		// given
		String description = "74348 Germany";

		// when
		Geocode geocode = this.geocodingService.getGeocodeFromDescription(description);

		// then
		assertThat(geocode.getLat()).isEqualTo(49.0769491);
		assertThat(geocode.getLng()).isEqualTo(9.1512248);
	}
}
