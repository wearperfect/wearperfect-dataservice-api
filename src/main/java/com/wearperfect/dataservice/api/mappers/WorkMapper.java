package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.WorkDTO;
import com.wearperfect.dataservice.api.entities.Work;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkMapper {
	
	@Mapping(source = "workedFrom", target = "workedFrom", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "workedTo", target = "workedTo", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	WorkDTO mapWorkToWorkDto(Work work);
	
	@Mapping(source = "workedFrom", target = "workedFrom", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "workedTo", target = "workedTo", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	Work mapWorkDtoToWork(WorkDTO workDto);

}
