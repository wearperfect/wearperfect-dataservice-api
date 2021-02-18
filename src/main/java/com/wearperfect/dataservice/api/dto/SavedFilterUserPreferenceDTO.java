package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class SavedFilterUserPreferenceDTO {

	Long id;

	Long savedFilterId;

	Long userId;

	UserBasicDetailsDTO user;

	Boolean active;
}
