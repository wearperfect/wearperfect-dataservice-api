package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PreferenceFilterColorDTO {
	Long id;
	Long preferenceFilterId;
	Integer colorId;
	ColorBasicDetailsDTO color;
	Boolean active;
}
