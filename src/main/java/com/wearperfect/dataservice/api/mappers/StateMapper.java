package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.wearperfect.dataservice.api.dto.StateBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.StateDTO;
import com.wearperfect.dataservice.api.entities.State;

@Mapper(uses= {UtilityMapper.class})
public interface StateMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	StateDTO mapStateToStateDto(State state);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	State mapStateDtoToState(StateDTO stateDto);
	
	StateBasicDetailsDTO mapStateToStateBasicDetailsDto(State state);
}
