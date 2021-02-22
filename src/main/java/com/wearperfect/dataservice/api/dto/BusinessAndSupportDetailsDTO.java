package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class BusinessAndSupportDetailsDTO {
	
	Long id;

	Long userId;

	String title;

	String addressLine1;

	String addressLine2;

	String landmark;

	Integer zipCode;

	CityBasicDetailsDTO city;

	StateBasicDetailsDTO state;

	CountryBasicDetailsDTO country;
	
	String geoLocation;

	String supportPhone;
	
	String supportEmail;
	
	String supportLink;

	Boolean active;
}
