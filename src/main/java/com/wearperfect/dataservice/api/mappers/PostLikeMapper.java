package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PostLikeDTO;
import com.wearperfect.dataservice.api.entities.PostLike;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostLikeMapper {

	PostLikeDTO mapPostLikeToPostLikeDto(PostLike postLike);

	PostLike mapPostLikeDtoToPostLike(PostLikeDTO postLikeDto);
}
