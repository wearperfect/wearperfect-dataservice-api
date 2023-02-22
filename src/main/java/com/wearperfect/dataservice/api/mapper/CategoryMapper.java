package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.CategoryBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.CategoryDTO;
import com.wearperfect.dataservice.api.entity.Category;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	CategoryDTO mapCategorytoCategoryDto(Category category);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	Category mapCategoryDtoToCategory(CategoryDTO categoryDto);
	
	CategoryBasicDetailsDTO mapCategoryToCategoryBasicDetailsDTO(Category category);
}
