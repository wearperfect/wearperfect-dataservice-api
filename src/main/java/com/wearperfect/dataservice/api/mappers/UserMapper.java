package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.entities.User;

@Mapper(uses = {User.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	@Mapping(ignore = true, target = "password")
	UserDTO mapUserToUserDto(User user);

	User mapUserDtoToUse(UserDTO userDto);
}
