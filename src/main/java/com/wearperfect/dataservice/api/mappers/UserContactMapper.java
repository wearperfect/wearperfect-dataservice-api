package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.UserContactDTO;
import com.wearperfect.dataservice.api.dto.UserContactDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserContactMessagesDTO;
import com.wearperfect.dataservice.api.entities.UserContact;

@Mapper(uses= {UtilityMapper.class, UserMapper.class, MessageMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserContactMapper {

	@Mapping(source = "firstContactedOn", target = "firstContactedOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastContactedOn", target = "lastContactedOn", qualifiedByName = "dateToTimeConverter")
	UserContactDTO mapUserContactToUserContactDto(UserContact userContact);

	@Mapping(source = "firstContactedOn", target = "firstContactedOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastContactedOn", target = "lastContactedOn", qualifiedByName = "timeToDateConverter")
	UserContact mapUserContactDtoToUserContact(UserContactDTO userContactDto);
	
	@Mapping(source = "userDetails", target = "userDetails")
	@Mapping(source = "contactUserDetails", target = "contactUserDetails")
	@Mapping(source = "firstContactedOn", target = "firstContactedOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastContactedOn", target = "lastContactedOn", qualifiedByName = "dateToTimeConverter")
	UserContactDetailsDTO mapUserContactToUserContactDetailsDto(UserContact userContact);
	
	@Mapping(source = "userDetails", target = "userDetails")
	@Mapping(source = "contactUserDetails", target = "contactUserDetails")
	@Mapping(source = "firstContactedOn", target = "firstContactedOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastContactedOn", target = "lastContactedOn", qualifiedByName = "dateToTimeConverter")
	UserContactMessagesDTO mapUserContactToUserContactMessagesDto(UserContact userContact);
}
