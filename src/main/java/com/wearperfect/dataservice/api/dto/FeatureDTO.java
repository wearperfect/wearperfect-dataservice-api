package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class FeatureDTO {

	Integer id;
	
	String desc;
	
	Integer sequenceId;
	
	Long createdBy;

	Long createdOn;

	Long lastUpdatedBy;

	Long lastUpdatedOn;
	
	Boolean active;
}
