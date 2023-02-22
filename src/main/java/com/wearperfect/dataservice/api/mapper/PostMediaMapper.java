package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PostMediaDTO;
import com.wearperfect.dataservice.api.dto.PostMediaDetailsDTO;
import com.wearperfect.dataservice.api.entity.PostMedia;

@Mapper(uses = { ContentTypeMapper.class, PostMediaUserTagMapper.class, UtilityMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMediaMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	PostMediaDTO mapPostMediaToPostMediaDto(PostMedia postMedia);

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	PostMedia mapPostMediaDtoToPostMedia(PostMediaDTO postItemDto);

	//@Mapping(source = "contentTypeDetails", target = "contentType")
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	PostMediaDetailsDTO mapPostMediaToPostMediaDetailsDTO(PostMedia postMedia);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	PostMedia mapPostMediaDetailsDtoToPostMedia(PostMediaDetailsDTO postMediaDetailsDto);
}
