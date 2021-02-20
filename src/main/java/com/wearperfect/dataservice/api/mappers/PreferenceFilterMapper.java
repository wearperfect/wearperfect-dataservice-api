package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.PreferenceFilterDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterDetailsDTO;
import com.wearperfect.dataservice.api.entities.PreferenceFilter;

@Mapper(uses = { PreferenceFilterCategoryMapper.class, PreferenceFilterColorMapper.class,
		PreferenceFilterGenderCategoryMapper.class, PreferenceFilterRegionMapper.class,
		PreferenceFilterStyleMapper.class, PreferenceFilterUserMapper.class,
		UtilityMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PreferenceFilterMapper {
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	PreferenceFilterDTO mapSavedFilterToSavedFilterDto(PreferenceFilter preferenceFilter);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	PreferenceFilter mapSavedFilterDtoToSavedFilter(PreferenceFilterDTO savedFilterDto);

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	PreferenceFilterDetailsDTO mapSavedFilterToSavedFilterDetailsDto(PreferenceFilter preferenceFilter);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	PreferenceFilter mapSavedFilterDetailsDtoToSavedFilter(PreferenceFilterDetailsDTO savedFilterDetailsDto);
}
