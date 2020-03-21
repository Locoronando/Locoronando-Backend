package org.wirvsvirus.locoronando.location;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenCageDataResponse {
	private List<OpenCageDataResult> results;

	public List<OpenCageDataResult> getResults() {
		return Collections.unmodifiableList(this.results);
	}
}
