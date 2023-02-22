package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PreferenceFilterUserDTO;
import com.wearperfect.dataservice.api.entity.PreferenceFilterUser;

@Mapper(uses= {UserMapper.class, UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PreferenceFilterUserMapper {

	PreferenceFilterUserDTO mapPreferenceFilterUserToPreferenceFilterUserDto(PreferenceFilterUser preferenceFilterUser);
	
	PreferenceFilterUser mapPreferenceFilterUserDtoToPreferenceFilterUser(PreferenceFilterUserDTO preferenceFilterUserDto);
}
