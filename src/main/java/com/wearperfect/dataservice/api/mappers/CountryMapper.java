package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.wearperfect.dataservice.api.dto.CountryBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.CountryDTO;
import com.wearperfect.dataservice.api.entities.Country;

@Mapper(uses= {UtilityMapper.class})
public interface CountryMapper {
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	CountryDTO mapCountryToCountryDto(Country country);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	Country mapCountryDtoToCountry(CountryDTO countryDto);
	
	CountryBasicDetailsDTO mapCountryToCountryBasicDetailsDto(Country country);
}
