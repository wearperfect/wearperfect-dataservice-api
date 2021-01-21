package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PostSaveDTO;
import com.wearperfect.dataservice.api.entities.PostSave;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostSaveMapper {

	PostSaveDTO mapPostSaveToPostSaveDto(PostSave postSave);

	PostSave mapPostSaveDtoToPostSave(PostSaveDTO postSaveDto);
}
