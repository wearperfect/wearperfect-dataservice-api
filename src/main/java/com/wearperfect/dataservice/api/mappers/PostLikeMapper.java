package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PostLikeDTO;
import com.wearperfect.dataservice.api.entities.PostLike;

@Mapper(uses = { UserMapper.class, UtilityMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostLikeMapper {

	@Mapping(source = "likedOn", target = "likedOn", qualifiedByName = "dateToTimeConverter")
	PostLikeDTO mapPostLikeToPostLikeDto(PostLike postLike);

	@Mapping(source = "likedOn", target = "likedOn", qualifiedByName = "timeToDateConverter")
	PostLike mapPostLikeDtoToPostLike(PostLikeDTO postLikeDto);
}
