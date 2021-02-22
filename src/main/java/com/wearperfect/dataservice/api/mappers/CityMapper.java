package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.CityBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.CityDTO;
import com.wearperfect.dataservice.api.entities.City;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	CityDTO mapCityToCityDto(City city);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	City mapCityDtoToCity(CityDTO cityDto);
	
	CityBasicDetailsDTO mapCityToCityBasicDetailsDto(City city);
}
