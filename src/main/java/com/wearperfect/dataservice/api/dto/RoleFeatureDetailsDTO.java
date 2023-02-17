package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class RoleFeatureDetailsDTO {
	Integer id;
	Integer roleId;
	Integer featureId;
	Boolean active;
	FeatureBasicDetailsDTO feature;
}
