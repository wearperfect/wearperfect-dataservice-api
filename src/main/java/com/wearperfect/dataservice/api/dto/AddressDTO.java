package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class AddressDTO {

	Long id;
	
	Long userId;
	
	String title;

	String addressLine1;
	
	String addressLine2;
	
	String landmark;

	Integer zipCode;
	
	CityDTO city;
	
	Integer cityId;

	StateDTO state;

	Integer stateId;
	
	CountryDTO country;
	
	Integer countryId;

	Long phone;
	
	Long createdBy;

    Long createdOn;

	Long lastUpdatedBy;

	Long lastUpdatedOn;
	
	Long lastUsedOn;

	Boolean active;
}
