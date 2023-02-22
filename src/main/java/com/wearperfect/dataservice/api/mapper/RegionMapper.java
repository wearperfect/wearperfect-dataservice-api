package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.RegionBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.RegionDTO;
import com.wearperfect.dataservice.api.entity.Region;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegionMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	RegionDTO mapRegionToRegionDto(Region region);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	Region mapRegionDtoToRegion(RegionDTO regionDto);
	
	RegionBasicDetailsDTO mapRegionToRegionBasicDetailsDto(Region region);
}
