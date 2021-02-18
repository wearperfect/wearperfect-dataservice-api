package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.SavedFilterColorPreferenceDTO;
import com.wearperfect.dataservice.api.entities.SavedFilterColorPreference;

@Mapper(uses= {ColorMapper.class, UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SavedFilterColorPreferenceMapper {
	
	SavedFilterColorPreferenceDTO mapSavedFilterColorPreferenceToSavedFilterColorPreferenceDto(SavedFilterColorPreference savedFilterColorPreference);
	
	SavedFilterColorPreference mapSavedFilterColorPreferenceDtoToSavedFilterColorPreference(SavedFilterColorPreferenceDTO savedFilterColorPreference);
}
