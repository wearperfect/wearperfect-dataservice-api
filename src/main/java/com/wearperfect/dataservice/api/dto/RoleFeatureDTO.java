package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class RoleFeatureDTO {
	
	Integer id;
	
	Integer roleId;
	
	Integer featureId;
	
	Long createdBy;

	Long createdOn;

	Long lastUpdatedBy;

	Long lastUpdatedOn;
	
	Boolean active;
}
