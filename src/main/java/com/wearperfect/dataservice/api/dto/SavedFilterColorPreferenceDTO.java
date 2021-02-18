package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class SavedFilterColorPreferenceDTO {

	Long id;

	Long savedFilterId;

	Integer colorId;

	ColorBasicDetailsDTO color;

	Boolean active;
}
