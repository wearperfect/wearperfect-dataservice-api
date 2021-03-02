package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PostItemDTO;
import com.wearperfect.dataservice.api.dto.PostItemDetailsDTO;
import com.wearperfect.dataservice.api.entities.PostItem;

@Mapper(uses = { ContentTypeMapper.class, UtilityMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostItemMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	PostItemDTO mapPostItemToPostItemDto(PostItem postItem);

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	PostItem mapPostItemDtoToPostItem(PostItemDTO postItemDto);

	//@Mapping(source = "contentTypeDetails", target = "contentType")
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	PostItemDetailsDTO mapPostItemToPostItemDetailsDTO(PostItem postItem);
}
