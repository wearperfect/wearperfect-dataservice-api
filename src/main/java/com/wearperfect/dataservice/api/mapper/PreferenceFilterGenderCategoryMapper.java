package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PreferenceFilterGenderCategoryDTO;
import com.wearperfect.dataservice.api.entity.PreferenceFilterGenderCategory;

@Mapper(uses= {UtilityMapper.class, GenderCategoryMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PreferenceFilterGenderCategoryMapper {
	
	PreferenceFilterGenderCategoryDTO mapPreferenceFilterGenderCategoryToPreferenceFilterGenderCategoryDto(PreferenceFilterGenderCategory preferenceFilterGenderCategory);
	
	PreferenceFilterGenderCategory mapPreferenceFilterGenderCategoryDtoToPreferenceFilterGenderCategory(PreferenceFilterGenderCategoryDTO preferenceFilterGenderCategoryDto);
}
