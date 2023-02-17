package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class StateDTO {
	Integer id;
	String name;
	String shortName;
	Long countryId;
	Long createdBy;
	Long createdOn;
	Long lastUpdatedBy;
	Long lastUpdatedOn;
	Boolean active;
}
