package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class CountryDTO {
	Integer id;
	String name;
	String shortName;
	String thumbnail;
	Long createdBy;
	Long createdOn;
	Long lastUpdatedBy;
	Long lastUpdatedOn;
	Boolean active;
}
