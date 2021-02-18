package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class CategoryDTO {

	Integer id;
	
	String name;
	
	String desc;
	
	Integer sequence;
	
	Long createdBy;

	Long createdOn;

	Long lastUpdatedBy;

	Long lastUpdatedOn;
	
	Boolean active;
}
