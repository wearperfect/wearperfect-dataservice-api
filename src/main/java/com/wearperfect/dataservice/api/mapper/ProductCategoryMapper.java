package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.ProductCategoryDTO;
import com.wearperfect.dataservice.api.entity.ProductCategory;

@Mapper(uses = { UtilityMapper.class, CategoryMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductCategoryMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	ProductCategoryDTO mapProductCategoryToProductCategoryDto(ProductCategory productCategory);

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	ProductCategory mapProductCategoryDtoToProductCategory(ProductCategoryDTO ProductCategoryDto);
}
