package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.GenderBasicDetailsDTO;
import com.wearperfect.dataservice.api.entities.Gender;

@Mapper(uses = { UtilityMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GenderMapper {
	GenderBasicDetailsDTO mapGenderToGenderBasicDetails(Gender gender);
}
