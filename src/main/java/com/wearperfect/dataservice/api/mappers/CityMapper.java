package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.wearperfect.dataservice.api.dto.CityDTO;
import com.wearperfect.dataservice.api.entities.City;

@Mapper(uses= {UtilityMapper.class})
public interface CityMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	CityDTO mapCityToCityDto(City city);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	City mapCityDtoToCity(CityDTO cityDto);
}
