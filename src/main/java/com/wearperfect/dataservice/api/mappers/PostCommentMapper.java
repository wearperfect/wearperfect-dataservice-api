package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PostCommentDTO;
import com.wearperfect.dataservice.api.dto.PostCommentDetailsDTO;
import com.wearperfect.dataservice.api.entities.PostComment;

@Mapper(uses= {UserMapper.class},  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostCommentMapper {

	PostCommentDTO mapPostCommentToPostCommentDto(PostComment postComment);
	
	PostComment mapPostCommentDtoToPostComment(PostCommentDTO postCommentDto);
	
	@Mapping(source = "commentedByUserDetails", target = "commentedBy")
	PostCommentDetailsDTO mapPostCommentToPostCommentDetailsDto(PostComment postComment);
}
