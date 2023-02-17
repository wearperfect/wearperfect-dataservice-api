package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PostDTO {
	Long id;
	String title;
	String description;
	Boolean active;
	Long createdBy;
	Long createdOn;
	Long lastUpdatedBy;
	Long lastUpdatedOn;
}
