package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PreferenceFilterStyleDTO {

	Long id;

	Long preferenceFilterId;

	Integer styleId;

	StyleBasicDetailsDTO style;

	Boolean active;
}
