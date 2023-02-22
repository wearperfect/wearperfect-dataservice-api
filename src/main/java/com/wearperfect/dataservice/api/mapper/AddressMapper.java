package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.AddressDTO;
import com.wearperfect.dataservice.api.dto.AddressDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.entity.Address;

@Mapper(uses= {CountryMapper.class, StateMapper.class, CityMapper.class, UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUsedOn", target = "lastUsedOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "cityDetails", target = "city")
	@Mapping(source = "stateDetails", target = "state")
	@Mapping(source = "countryDetails", target = "country")
	AddressDTO mapAddressToAddressDto(Address address);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUsedOn", target = "lastUsedOn", qualifiedByName = "timeToDateConverter")
	Address mapAddressDtoToAddress(AddressDTO addressDto);
	
	@Mapping(source = "lastUsedOn", target = "lastUsedOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "cityDetails", target = "city")
	@Mapping(source = "stateDetails", target = "state")
	@Mapping(source = "countryDetails", target = "country")
    AddressDetailsDTO mapAddressToAddressDetailsDto(Address address);
}
