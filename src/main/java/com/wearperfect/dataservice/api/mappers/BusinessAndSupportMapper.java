package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.BusinessAndSupportDTO;
import com.wearperfect.dataservice.api.entities.BusinessAndSupport;

@Mapper(uses= {CountryMapper.class, StateMapper.class, CityMapper.class, UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BusinessAndSupportMapper {
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "cityDetails", target = "city")
	@Mapping(source = "stateDetails", target = "state")
	@Mapping(source = "countryDetails", target = "country")
	BusinessAndSupportDTO mapBusinessAndSupporToBusinessAndSupportDto(BusinessAndSupport businessAndSupport);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	BusinessAndSupport mapBusinessAndSupportDtoToBusinessAndSupport(BusinessAndSupportDTO businessAndSupportDto);
}
