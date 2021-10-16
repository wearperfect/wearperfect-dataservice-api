package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PostMediaUserTagDTO;
import com.wearperfect.dataservice.api.dto.PostMediaUserTagDetailsDto;
import com.wearperfect.dataservice.api.entities.PostMediaUserTag;

@Mapper(uses = { ContentTypeMapper.class, PostMediaMapper.class, UserMapper.class, UtilityMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMediaUserTagMapper {
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	PostMediaUserTagDTO mapPostMediaUserTagToPostMediaUserTagDto(PostMediaUserTag postMediaUserTag);

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	PostMediaUserTag mapPostMediaUserTagDtoToPostMediaUserTag(PostMediaUserTagDTO postMediaUserTagDTO);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	PostMediaUserTagDetailsDto mapPostMediaUserTagToPostMediaUserTagDetailsDto(PostMediaUserTag postMediaUserTag);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	PostMediaUserTag mapPostMediaUserTagDetailsDtoToPostMediaUserTag(PostMediaUserTagDetailsDto postMediaUserTagDetailsDto);
}