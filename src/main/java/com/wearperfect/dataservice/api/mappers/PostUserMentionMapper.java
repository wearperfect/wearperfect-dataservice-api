package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PostUserMentionDTO;
import com.wearperfect.dataservice.api.dto.PostUserMentionDetailsDTO;
import com.wearperfect.dataservice.api.entities.PostUserMention;

@Mapper(uses = { UserMapper.class, PostMapper.class, UtilityMapper.class },
unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostUserMentionMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	PostUserMentionDTO mapPostUserMentionToPostUserMentionDto(PostUserMention postUserMention);

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	PostUserMention mapPostUserMentionDtoToPostUserMention(PostUserMentionDTO postUserMentionDto);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	PostUserMentionDetailsDTO mapPostUserMentionToPostUserMentionDetailsDto(PostUserMention postUserMention);
}
