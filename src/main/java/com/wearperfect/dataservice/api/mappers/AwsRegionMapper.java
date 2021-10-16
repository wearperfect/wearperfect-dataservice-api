package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.AwsRegionDTO;
import com.wearperfect.dataservice.api.entities.AwsRegion;

@Mapper(uses = { UtilityMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AwsRegionMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	AwsRegionDTO mapAwsRegionToAwsRegionDto(AwsRegion awsRegion);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	AwsRegion mapAwsRegionDtoToAwsRegion(AwsRegionDTO awsRegionDto);
}
