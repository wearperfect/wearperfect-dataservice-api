package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class StyleDTO {
	Integer id;
	String name;
	String desc;
	String thumbnail;
	String sourceLink;
	Long createdBy;
	Long createdOn;
	Long lastUpdatedBy;
	Long lastUpdatedOn;
	Boolean active;
}
