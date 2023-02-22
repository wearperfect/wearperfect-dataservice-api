package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.ColorBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ColorDTO;
import com.wearperfect.dataservice.api.entity.Color;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ColorMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	ColorDTO mapColorToColorDto(Color color);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	Color mapColorDtoToColor(ColorDTO colorDto);
	
	ColorBasicDetailsDTO mapColorToColorBasicDetailsDto(Color color);
}
