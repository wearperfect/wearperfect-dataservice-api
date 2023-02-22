package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.StateBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.StateDTO;
import com.wearperfect.dataservice.api.dto.StateDetailsDTO;
import com.wearperfect.dataservice.api.entity.State;

@Mapper(uses= {UtilityMapper.class, CityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StateMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	StateDTO mapStateToStateDto(State state);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	State mapStateDtoToState(StateDTO stateDto);
	
	StateBasicDetailsDTO mapStateToStateBasicDetailsDto(State state);
	
	StateDetailsDTO mapStateToStateDetailsDto(State state);
}
