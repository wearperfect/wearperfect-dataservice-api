package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PreferenceFilterColorDTO;
import com.wearperfect.dataservice.api.entity.PreferenceFilterColor;

@Mapper(uses= {ColorMapper.class, UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PreferenceFilterColorMapper {
	
	PreferenceFilterColorDTO mapPreferenceFilterColorToPreferenceFilterColorDto(PreferenceFilterColor preferenceFilterColor);
	
	PreferenceFilterColor mapPreferenceFilterColorDtoToPreferenceFilterColor(PreferenceFilterColorDTO preferenceFilterColorDto);
}
