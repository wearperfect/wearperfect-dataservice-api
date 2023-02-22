package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.ContentTypeBasicDetailsDTO;
import com.wearperfect.dataservice.api.entity.ContentType;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContentTypeMapper {

	ContentTypeBasicDetailsDTO mapContentTypeToContentTypeBasicDetailsDTO(ContentType contentType);
}
