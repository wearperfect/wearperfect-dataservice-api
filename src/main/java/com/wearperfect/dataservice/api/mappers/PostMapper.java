package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.dto.PostItemDetailsDTO;
import com.wearperfect.dataservice.api.entities.Post;
import com.wearperfect.dataservice.api.entities.PostItem;

@Mapper(uses = { UserMapper.class, PostItemMapper.class, PostCommentMapper.class },
		unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
	
	PostDTO mapPostToPostDto(Post post);

	Post mapPostDtoToPost(PostDTO postDto);
	
	@Mapping(source = "createdByUserDetails", target = "createdBy")
	@Mapping(source = "lastUpdatedByUserDetails", target = "lastUpdatedBy")
	PostDetailsDTO mapPostToPostDetailsDto(Post post);
}
