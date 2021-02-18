package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.SavedFilterDTO;
import com.wearperfect.dataservice.api.dto.SavedFilterDetailsDTO;
import com.wearperfect.dataservice.api.entities.SavedFilter;

@Mapper(uses = { SavedFilterCategoryPreferenceMapper.class, SavedFilterColorPreferenceMapper.class,
		SavedFilterGenderCategoryPreferenceMapper.class, SavedFilterRegionPreferenceMapper.class,
		SavedFilterStylePreferenceMapper.class, SavedFilterUserPreferenceMapper.class,
		UtilityMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SavedFilterMapper {
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	SavedFilterDTO mapSavedFilterToSavedFilterDto(SavedFilter savedFilter);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	SavedFilter mapSavedFilterDtoToSavedFilter(SavedFilterDTO savedFilterDto);

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	SavedFilterDetailsDTO mapSavedFilterToSavedFilterDetailsDto(SavedFilter savedFilter);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	SavedFilter mapSavedFilterDetailsDtoToSavedFilter(SavedFilterDetailsDTO savedFilterDetailsDto);
}
