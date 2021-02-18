package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class SavedFilterStylePreferenceDTO {

	Long id;

	Long savedFilterId;

	Integer styleId;

	StyleBasicDetailsDTO style;

	Boolean active;
}
