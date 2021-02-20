package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PreferenceFilterStyleDTO;
import com.wearperfect.dataservice.api.entities.PreferenceFilterStyle;

@Mapper(uses= {StyleMapper.class, UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PreferenceFilterStyleMapper {

	PreferenceFilterStyleDTO mapPreferenceFilterStyleToPreferenceFilterStyleDto(PreferenceFilterStyle preferenceFilterStyle);
	
	PreferenceFilterStyle mapPreferenceFilterStyleDtoToPreferenceFilterStyle(PreferenceFilterStyleDTO preferenceFilterStyleDto);
}
