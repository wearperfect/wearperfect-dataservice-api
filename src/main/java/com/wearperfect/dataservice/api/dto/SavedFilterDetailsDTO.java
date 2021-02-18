package com.wearperfect.dataservice.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class SavedFilterDetailsDTO {

	Long id;
	
	String title;
	
	String description;
	
	Long userId;
	
	Long createdBy;

	Long createdOn;
	
	Long lastUpdatedBy;

	Long lastUpdatedOn;

	Boolean active;
	
	List<SavedFilterGenderCategoryPreferenceDTO> genderCategories;
	
	List<SavedFilterCategoryPreferenceDTO> categories;
	
	List<SavedFilterRegionPreferenceDTO> regions;
	
	List<SavedFilterStylePreferenceDTO> styles;
	
	List<SavedFilterColorPreferenceDTO> colors;
	
	List<SavedFilterUserPreferenceDTO> users;
}
