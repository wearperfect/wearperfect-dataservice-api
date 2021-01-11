package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.BasicContentTypeDetailsDTO;
import com.wearperfect.dataservice.api.entities.ContentType;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BasicContentTypeDetailsMapper {

	BasicContentTypeDetailsDTO mapContentTypeToBasicContentTypeDetailsDTO(ContentType contentType);
}
