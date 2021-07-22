package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class BusinessAndSupportDTO {

	Long id;

	Long userId;

	String title;

	String addressLine1;

	String addressLine2;

	String landmark;

	String zipCode;

	CityBasicDetailsDTO city;

	Integer cityId;

	StateBasicDetailsDTO state;

	Integer stateId;

	CountryBasicDetailsDTO country;

	Integer countryId;
	
	String geoLocation;

	String supportPhone;
	
	String supportEmail;
	
	String supportLink;

	Long createdBy;

	Long createdOn;

	Long lastUpdatedBy;

	Long lastUpdatedOn;

	Boolean active;
}
