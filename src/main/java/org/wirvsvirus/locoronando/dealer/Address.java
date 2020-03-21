package org.wirvsvirus.locoronando.dealer;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {
	private String street;
	private String houseNumber;
	private int postalCode;
}
