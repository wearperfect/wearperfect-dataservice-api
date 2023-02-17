package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class UserContactDetailsDTO {
	Integer id;
	Long userId;
	Long contactUserId;
	Long firstContactedOn;
	Long lastContactedOn;
	Boolean active;
	UserBasicDetailsDTO userDetails;
	UserBasicDetailsDTO contactUserDetails;
}
