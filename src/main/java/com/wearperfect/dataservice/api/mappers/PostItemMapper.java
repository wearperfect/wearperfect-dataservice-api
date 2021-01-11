package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PostItemDTO;
import com.wearperfect.dataservice.api.entities.PostItem;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostItemMapper {

	PostItemDTO mapPostItemToPostItemDto(PostItem postItem);

	PostItem mapPostItemDtoToPostItem(PostItemDTO postItemDto);
}
