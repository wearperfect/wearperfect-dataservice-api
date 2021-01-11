package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.entities.Post;

@Mapper(uses = { PostItemMapper.class },
		unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
	
	PostDTO mapPostToPostDto(Post post);

	Post mapPostDtoToPost(PostDTO postDto);
}
