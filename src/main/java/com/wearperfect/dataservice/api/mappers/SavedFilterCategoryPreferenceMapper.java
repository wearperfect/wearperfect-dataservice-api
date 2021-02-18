package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.SavedFilterCategoryPreferenceDTO;
import com.wearperfect.dataservice.api.entities.SavedFilterCategoryPreference;

@Mapper(uses= {CategoryMapper.class, UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SavedFilterCategoryPreferenceMapper {
	
	SavedFilterCategoryPreferenceDTO mapSavedFilterCategoryPreferenceToSavedFilterCategoryPreferenceDto(SavedFilterCategoryPreference savedFilterCategoryPreference);
	
	SavedFilterCategoryPreference mapSavedFilterCategoryPreferenceDtoToSavedFilterCategoryPreference(SavedFilterCategoryPreferenceDTO savedFilterCategoryPreference);
}
