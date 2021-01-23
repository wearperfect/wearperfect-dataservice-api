package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PostItemDTO;
import com.wearperfect.dataservice.api.dto.PostItemDetailsDTO;
import com.wearperfect.dataservice.api.entities.PostItem;

@Mapper(uses = { ContentTypeMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostItemMapper {

	PostItemDTO mapPostItemToPostItemDto(PostItem postItem);

	PostItem mapPostItemDtoToPostItem(PostItemDTO postItemDto);

	@Mapping(source = "contentTypeDetails", target = "contentType")
	PostItemDetailsDTO mapPostItemToPostItemDetailsDTO(PostItem postItem);
}
