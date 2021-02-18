package com.wearperfect.dataservice.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.GenderCategoryBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.GenderCategoryDTO;
import com.wearperfect.dataservice.api.entities.GenderCategory;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GenderCategoryMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	GenderCategoryDTO mapGenderCategoryToGenderCategoryDto(GenderCategory genderCategory); 
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	GenderCategory mapGenderCategoryDtoToGenderCategory(GenderCategoryDTO genderCategoryDto);
	
	GenderCategoryBasicDetailsDTO mapGenderCategoryToGenderCategoryBasicDetailsDto(GenderCategory genderCategory);
}
