package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.SavedFilterGenderCategoryPreferenceDTO;
import com.wearperfect.dataservice.api.entities.SavedFilterGenderCategoryPreference;

@Mapper(uses= {UtilityMapper.class, GenderCategoryMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SavedFilterGenderCategoryPreferenceMapper {
	
	SavedFilterGenderCategoryPreferenceDTO mapSavedFilterGenderCategoryPreferenceToSavedFilterGenderCategoryPreferenceDto(SavedFilterGenderCategoryPreference savedFilterGenderCategoryPreference);
	
	SavedFilterGenderCategoryPreference mapSavedFilterGenderCategoryPreferenceDtoToSavedFilterGenderCategoryPreference(SavedFilterGenderCategoryPreferenceDTO savedFilterGenderCategoryPreferenceDto);
}
