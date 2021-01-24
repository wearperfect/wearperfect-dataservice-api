package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PostSaveDTO;
import com.wearperfect.dataservice.api.entities.PostSave;

@Mapper(uses = { UtilityMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostSaveMapper {

	@Mapping(source = "savedOn", target = "savedOn", qualifiedByName = "dateToTimeConverter")
	PostSaveDTO mapPostSaveToPostSaveDto(PostSave postSave);

	@Mapping(source = "savedOn", target = "savedOn", qualifiedByName = "timeToDateConverter")
	PostSave mapPostSaveDtoToPostSave(PostSaveDTO postSaveDto);
}
