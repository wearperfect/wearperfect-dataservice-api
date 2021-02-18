package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class SavedFilterGenderCategoryPreferenceDTO {

	Long id;

	Long savedFilterId;

	Integer genderCategoryId;
	
	GenderCategoryBasicDetailsDTO genderCategory;

	Boolean active;
	
}
