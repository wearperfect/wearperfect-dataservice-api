package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.SavedFilterRegionPreferenceDTO;
import com.wearperfect.dataservice.api.entities.SavedFilterRegionPreference;

@Mapper(uses= {RegionMapper.class, UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SavedFilterRegionPreferenceMapper {

	SavedFilterRegionPreferenceDTO mapSavedFilterRegionPreferenceToSavedFilterRegionPreferenceDto(SavedFilterRegionPreference savedFilterRegionPreference);
	
	SavedFilterRegionPreference  mapSavedFilterRegionPreferenceDtoToSavedFilterRegionPreference(SavedFilterRegionPreferenceDTO SavedFilterRegionPreferenceDto);
}
