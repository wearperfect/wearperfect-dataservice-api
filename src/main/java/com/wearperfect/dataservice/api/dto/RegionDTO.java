package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class RegionDTO {

	Integer id;

	String name;

	String shortName;

	String desc;

	String thumbnail;

	String sourceLink;

	Long createdBy;

	Long createdOn;

	Long lastUpdatedBy;

	Long lastUpdatedOn;

	Boolean active;
}
