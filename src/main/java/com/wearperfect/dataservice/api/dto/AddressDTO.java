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
	
	CityBasicDetailsDTO city;
	
	Integer cityId;

	StateBasicDetailsDTO state;

	Integer stateId;
	
	CountryBasicDetailsDTO country;
	
	Integer countryId;
	
	String geoLocation;

	String phone;
	
	Long createdBy;

    Long createdOn;

	Long lastUpdatedBy;

	Long lastUpdatedOn;
	
	Long lastUsedOn;

	Boolean active;
}
