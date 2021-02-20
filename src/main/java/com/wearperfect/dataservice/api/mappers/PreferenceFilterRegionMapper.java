package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PreferenceFilterRegionDTO;
import com.wearperfect.dataservice.api.entities.PreferenceFilterRegion;

@Mapper(uses= {RegionMapper.class, UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PreferenceFilterRegionMapper {

	PreferenceFilterRegionDTO mapPreferenceFilterRegionToPreferenceFilterRegionDto(PreferenceFilterRegion preferenceFilterRegion);
	
	PreferenceFilterRegion  mapPreferenceFilterRegionDtoToPreferenceFilterRegion(PreferenceFilterRegionDTO preferenceFilterRegionDto);
}
