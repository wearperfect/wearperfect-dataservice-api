package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.entities.User;

@Mapper(uses = User.class)
public interface UserMapper {

	@Mapping(ignore = true, target = "password")
	UserDTO mapUserToUserDTO(User user);

	User mapUserDtoToUse(UserDTO userDto);
}
