package com.wearperfect.dataservice.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FiltersResponseDTO {
	List<CategoryBasicDetailsDTO> categories;
	List<ColorBasicDetailsDTO> colors;
	List<GenderCategoryBasicDetailsDTO> genderCategories;
	List<RegionBasicDetailsDTO> regions;
	List<StyleBasicDetailsDTO> styles;
}
