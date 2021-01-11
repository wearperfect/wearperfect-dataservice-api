package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.BasicUserDetailsDTO;
import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.entities.Post;

@Mapper(uses = {BasicUserDetailsMapper.class, PostItemDetailsMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostDetailsMapper {

	@Mapping(source = "createdByUserDetails", target = "createdBy")
	@Mapping(source = "lastUpdatedByUserDetails", target = "lastUpdatedBy")
	PostDetailsDTO mapPostToPostDetailsDto(Post post);
	
}
