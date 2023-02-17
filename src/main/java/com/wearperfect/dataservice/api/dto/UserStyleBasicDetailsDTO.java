package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class UserStyleBasicDetailsDTO {
	Long id;
	Long userId;
	Integer styleId;
	Boolean active;
}
