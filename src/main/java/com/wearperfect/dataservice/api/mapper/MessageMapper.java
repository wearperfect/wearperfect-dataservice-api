package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.MessageDTO;
import com.wearperfect.dataservice.api.dto.MessageDetailsDTO;
import com.wearperfect.dataservice.api.entity.Message;

@Mapper(uses= {UtilityMapper.class, UserMapper.class, ContentTypeMapper.class, PostMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "receivedOn", target = "receivedOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "seenOn", target = "seenOn", qualifiedByName = "dateToTimeConverter")
	MessageDTO mapMessageToMessageDto(Message message);

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "receivedOn", target = "receivedOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "seenOn", target = "seenOn", qualifiedByName = "timeToDateConverter")
	Message mapMessageDtoToMessage(MessageDTO messageDto);
	
	@Mapping(source = "sentByUserDetails", target = "sentByUserDetails")
	@Mapping(source = "sentToUserDetails", target = "sentToUserDetails")
	@Mapping(source = "mediaTypeDetails", target = "mediaTypeDetails")
	@Mapping(source = "postDetails", target = "postDetails")
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "receivedOn", target = "receivedOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "seenOn", target = "seenOn", qualifiedByName = "dateToTimeConverter")
	MessageDetailsDTO mapMessageToMessageDetailsDto(Message message);
}
