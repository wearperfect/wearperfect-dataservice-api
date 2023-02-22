package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PreferenceFilterCategoryDTO;
import com.wearperfect.dataservice.api.entity.PreferenceFilterCategory;

@Mapper(uses= {CategoryMapper.class, UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PreferenceFilterCategoryMapper {
	
	PreferenceFilterCategoryDTO mapPreferenceFilterCategoryToPreferenceFilterCategoryDto(PreferenceFilterCategory preferenceFilterCategory);
	
	PreferenceFilterCategory mapPreferenceFilterCategoryDtoToPreferenceFilterCategory(PreferenceFilterCategoryDTO preferenceFilterCategoryDto);
}
