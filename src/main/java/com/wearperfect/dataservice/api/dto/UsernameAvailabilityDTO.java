package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class UsernameAvailabilityDTO {

	String username;
	
	Boolean available;
}
