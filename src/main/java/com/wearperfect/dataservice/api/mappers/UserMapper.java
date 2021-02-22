package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.UserBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.dto.UserDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserFollowUpDetailsDTO;
import com.wearperfect.dataservice.api.entities.User;

@Mapper(uses = {User.class, UtilityMapper.class, RoleMapper.class, GenderMapper.class,
		AddressMapper.class, BusinessAndSupportMapper.class, CityMapper.class, StateMapper.class, CountryMapper.class}, 
unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	@Mapping(ignore = true, target = "password")
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "dob", target = "dob", qualifiedByName = "dateToTimeConverter")
	UserDTO mapUserToUserDto(User user);

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "dob", target = "dob", qualifiedByName = "timeToDateConverter")
	User mapUserDtoToUser(UserDTO userDto);	
	
	@Mapping(source = "roleDetails", target = "role")
	@Mapping(source = "genderDetails", target = "gender")
	@Mapping(source = "homeAddressDetails", target = "homeAddress")
	@Mapping(source = "currentAddressDetails", target = "currentAddress")
	@Mapping(source = "deliveryAddressDetails", target = "deliveryAddress")
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "dob", target = "dob", qualifiedByName = "dateToTimeConverter")
	UserDetailsDTO mapUserToUserDetailsDto(User user);
	
	@Mapping(source = "id", target = "userId")
	UserFollowUpDetailsDTO mapUserToUserFollowUpDetailsDto(User user);
	
	UserBasicDetailsDTO mapUserToUserBasicDetailsDto(User user);
}
