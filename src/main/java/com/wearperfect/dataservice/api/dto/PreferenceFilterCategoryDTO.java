package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PreferenceFilterCategoryDTO {
	Long id;
	Long preferenceFilterId;
	Integer categoryId;
	CategoryBasicDetailsDTO category;
	Boolean active;
}
