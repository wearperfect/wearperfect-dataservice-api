package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class AwsRegionDTO {

	Integer id;

	String name;

	String description;

	Long createdOn;

	Long createdBy;

	Long lastUpdatedOn;

	Long lastUpdatedBy;

	Boolean active;
}
