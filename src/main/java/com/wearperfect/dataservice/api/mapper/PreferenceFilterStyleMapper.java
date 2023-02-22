package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PreferenceFilterStyleDTO;
import com.wearperfect.dataservice.api.entity.PreferenceFilterStyle;

@Mapper(uses= {StyleMapper.class, UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PreferenceFilterStyleMapper {

	PreferenceFilterStyleDTO mapPreferenceFilterStyleToPreferenceFilterStyleDto(PreferenceFilterStyle preferenceFilterStyle);
	
	PreferenceFilterStyle mapPreferenceFilterStyleDtoToPreferenceFilterStyle(PreferenceFilterStyleDTO preferenceFilterStyleDto);
}
