package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class SavedFilterDTO {

	Long id;

	String title;

	String description;

	Long userId;

	Long createdBy;

	Long createdOn;

	Long lastUpdatedBy;

	Long lastUpdatedOn;

	Boolean active;
}
