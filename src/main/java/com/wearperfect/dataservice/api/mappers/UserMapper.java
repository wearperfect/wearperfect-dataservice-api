package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.BasicUserDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.entities.User;

@Mapper(uses = {User.class, UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	@Mapping(ignore = true, target = "password")
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	UserDTO mapUserToUserDto(User user);

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	User mapUserDtoToUse(UserDTO userDto);
	
	BasicUserDetailsDTO mapUserToBasicUserDetailsDto(User user);	
}
