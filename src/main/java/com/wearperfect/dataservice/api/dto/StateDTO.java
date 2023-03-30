package com.wearperfect.dataservice.api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
