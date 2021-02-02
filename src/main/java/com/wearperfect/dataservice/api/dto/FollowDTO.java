package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class FollowDTO {

	Long id;

	Long userId;

	Long followingBy;

	Boolean active;

	Long createdOn;

	Long lastUpdatedOn;
}
