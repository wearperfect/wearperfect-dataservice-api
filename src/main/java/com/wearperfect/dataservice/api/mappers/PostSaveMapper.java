package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PostSaveDTO;
import com.wearperfect.dataservice.api.dto.PostSaveDetailsDTO;
import com.wearperfect.dataservice.api.entities.PostSave;

@Mapper(uses = { PostMapper.class, UserMapper.class, UtilityMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostSaveMapper {

	@Mapping(source = "savedOn", target = "savedOn", qualifiedByName = "dateToTimeConverter")
	PostSaveDTO mapPostSaveToPostSaveDto(PostSave postSave);

	@Mapping(source = "savedOn", target = "savedOn", qualifiedByName = "timeToDateConverter")
	PostSave mapPostSaveDtoToPostSave(PostSaveDTO postSaveDto);
	
	@Mapping(source = "savedOn", target = "savedOn", qualifiedByName = "dateToTimeConverter")
	PostSaveDetailsDTO mapPostSaveToPostSaveDetailsDto(PostSave postSave);
}
