package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class GenderCategoryDTO {
	Integer id;
	String name;
	String shortName;
	Long createdBy;
	Long createdOn;
	Long lastUpdatedBy;
	Long lastUpdatedOn;
	Boolean active;
}
