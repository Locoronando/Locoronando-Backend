package org.wirvsvirus.locoronando.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocodingService {

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
	public Geocode getGeocodeFromDescription(String description) {
		OpenCageDataResponse response = this.restTemplate.getForObject(this.url, OpenCageDataResponse.class,
				this.apikey, description);
		return response.getResults().get(0).getGeometry();
	}

}
