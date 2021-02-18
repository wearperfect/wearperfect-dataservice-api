package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.SavedFilterUserPreferenceDTO;
import com.wearperfect.dataservice.api.entities.SavedFilterUserPreference;

@Mapper(uses= {UserMapper.class, UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SavedFilterUserPreferenceMapper {

	SavedFilterUserPreferenceDTO mapSavedFilterUserPreferenceToSavedFilterUserPreferenceDto(SavedFilterUserPreference savedFilterUserPreference);
	
	SavedFilterUserPreference mapSavedFilterUserPreferenceDtoToSavedFilterUserPreference(SavedFilterUserPreferenceDTO savedFilterUserPreferenceDto);
}
