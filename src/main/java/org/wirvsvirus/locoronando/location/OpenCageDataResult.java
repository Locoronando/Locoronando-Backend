package org.wirvsvirus.locoronando.location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenCageDataResult {
	private Geocode geometry;

	public Geocode getGeometry() {
		return this.geometry;
	}
}
