package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PreferenceFilterUserDTO {
	Long id;
	Long preferenceFilterId;
	Long userId;
	UserBasicDetailsDTO user;
	Boolean active;
}
