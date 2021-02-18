package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class SavedFilterCategoryPreferenceDTO {

	Long id;
	
	Long savedFilterId;
	
	Integer categoryId;
	
	CategoryBasicDetailsDTO category;

	Boolean active;
}
