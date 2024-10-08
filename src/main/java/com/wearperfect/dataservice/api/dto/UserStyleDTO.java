package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class UserStyleDTO {
	Long id;
	Long userId;
	Integer styleId;
	Long createdBy;
	Long createdOn;
	Long lastUpdatedBy;
	Long lastUpdatedOn;
	Boolean active;
}
