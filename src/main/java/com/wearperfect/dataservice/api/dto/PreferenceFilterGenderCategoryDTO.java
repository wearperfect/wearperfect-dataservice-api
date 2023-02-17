package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PreferenceFilterGenderCategoryDTO {
	Long id;
	Long preferenceFilterId;
	Integer genderCategoryId;
	GenderCategoryBasicDetailsDTO genderCategory;
	Boolean active;
}
