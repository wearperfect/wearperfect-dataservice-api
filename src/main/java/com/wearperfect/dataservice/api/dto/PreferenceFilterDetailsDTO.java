package com.wearperfect.dataservice.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class PreferenceFilterDetailsDTO {

	Long id;
	
	String title;
	
	String description;
	
	Long userId;
	
	Long createdBy;

	Long createdOn;
	
	Long lastUpdatedBy;

	Long lastUpdatedOn;

	Boolean active;
	
	List<PreferenceFilterGenderCategoryDTO> genderCategories;
	
	List<PreferenceFilterCategoryDTO> categories;
	
	List<PreferenceFilterRegionDTO> regions;
	
	List<PreferenceFilterStyleDTO> styles;
	
	List<PreferenceFilterColorDTO> colors;
	
	List<PreferenceFilterUserDTO> users;
}
