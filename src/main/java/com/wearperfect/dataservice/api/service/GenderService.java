package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.GenderCategoryDTO;
import com.wearperfect.dataservice.api.dto.GenderDTO;

public interface GenderService {

	List<GenderDTO> getAllgenders();

	GenderDTO getGenderBygenderId(Integer genderId);

	GenderDTO createGender(GenderDTO genderDto);

	GenderDTO updateGender(Integer genderId, GenderDTO genderDto);

	GenderDTO deleteGender(Integer genderId);

	List<GenderCategoryDTO> getAllGenderCategories();

	GenderCategoryDTO getGenderCategoryByGenderCategoryId(Integer genderCategoryId);

	GenderCategoryDTO createGenderCategory(GenderCategoryDTO genderCategoryDto);

	GenderCategoryDTO updateGenderCategory(Integer genderCategoryId, GenderCategoryDTO genderCategoryDto);

	GenderCategoryDTO deleteGenderCategory(Integer genderCategoryId);

}
