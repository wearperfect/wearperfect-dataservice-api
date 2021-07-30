package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class UserContactDTO {

	Integer id;

	Long userId;

	Long contactUserId;
	
	Long firstContactedOn;
	
	Long lastContactedOn;

	Boolean active;
}
