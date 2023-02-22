package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PostCommentDTO;
import com.wearperfect.dataservice.api.dto.PostCommentDetailsDTO;
import com.wearperfect.dataservice.api.entity.PostComment;

@Mapper(uses= {UserMapper.class, UtilityMapper.class},  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostCommentMapper {

	@Mapping(source = "commentedOn", target = "commentedOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	PostCommentDTO mapPostCommentToPostCommentDto(PostComment postComment);
	
	@Mapping(source = "commentedOn", target = "commentedOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	PostComment mapPostCommentDtoToPostComment(PostCommentDTO postCommentDto);
	
	@Mapping(source = "commentedByUserDetails", target = "commentedBy")
	@Mapping(source = "commentedOn", target = "commentedOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	PostCommentDetailsDTO mapPostCommentToPostCommentDetailsDto(PostComment postComment);
}
