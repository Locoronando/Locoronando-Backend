package org.wirvsvirus.locoronando.dealer.model.db;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {
	private String street;
	private String houseNumber;
	private String postalCode;
}
