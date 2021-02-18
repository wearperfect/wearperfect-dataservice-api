package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.SavedFilterStylePreferenceDTO;
import com.wearperfect.dataservice.api.entities.SavedFilterStylePreference;

@Mapper(uses= {StyleMapper.class, UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SavedFilterStylePreferenceMapper {

	SavedFilterStylePreferenceDTO mapSavedFilterStylePreferenceToSavedFilterStylePreferenceDto(SavedFilterStylePreference savedFilterStylePreference);
	
	SavedFilterStylePreference mapSavedFilterStylePreferenceDtoToSavedFilterStylePreference(SavedFilterStylePreferenceDTO savedFilterStylePreferenceDto);
}
