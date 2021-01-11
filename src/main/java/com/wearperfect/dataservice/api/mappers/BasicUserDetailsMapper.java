package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.BasicUserDetailsDTO;
import com.wearperfect.dataservice.api.entities.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BasicUserDetailsMapper {

	BasicUserDetailsDTO mapUserToBasicUserDetailsDto(User user);
}
