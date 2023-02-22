package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.GenderBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.GenderDTO;
import com.wearperfect.dataservice.api.entity.Gender;

@Mapper(uses = { UtilityMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GenderMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	GenderDTO mapGenderToGenderDto(Gender gender);

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	Gender mapGenderDtoToGender(GenderDTO genderDto);
	
	GenderBasicDetailsDTO mapGenderToGenderBasicDetails(Gender gender);
}
