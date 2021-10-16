package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PostHashTagDTO;
import com.wearperfect.dataservice.api.entities.PostHashTag;

@Mapper(uses= {UtilityMapper.class},  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostHashTagMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	PostHashTagDTO mapPostHashTagToPostHashTagDto(PostHashTag postHashTag);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	PostHashTag mapPostHashTagDtoToPostHashTag(PostHashTagDTO postHashTagDto);
	
}
