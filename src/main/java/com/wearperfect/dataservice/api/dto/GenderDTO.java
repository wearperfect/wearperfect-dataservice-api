package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class GenderDTO {

	Integer id;
	
	String name;
	
	String shortName;
	
	Boolean active;
	
	Long createdBy;
	
	Long createdOn;
	
	Long lastUpdatedBy;
	
	Long lastUpdatedOn;
}