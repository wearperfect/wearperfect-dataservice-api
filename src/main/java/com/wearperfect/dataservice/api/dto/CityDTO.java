package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class CityDTO {

	Integer id;
	
	String name;
	
	Long stateId;
	
	Long createdBy;

	Long createdOn;

	Long lastUpdatedBy;

	Long lastUpdatedOn;
	
	Boolean active;
}
