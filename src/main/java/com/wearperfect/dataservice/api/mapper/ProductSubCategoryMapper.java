package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ProductSubCategoryBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductSubCategoryDTO;
import com.wearperfect.dataservice.api.dto.ProductSubCategoryDetailsDTO;
import com.wearperfect.dataservice.api.entity.ProductSubCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = { UtilityMapper.class, ProductCategoryMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductSubCategoryMapper {

    ProductSubCategory mapProductSubCategoryDtoToProductSubCategory(ProductSubCategoryDTO productSubCategoryDto);

    ProductSubCategoryDTO mapProductSubCategoryToProductSubCategoryDto(ProductSubCategory productSubCategory);

    ProductSubCategoryBasicDetailsDTO mapProductSubCategoryToProductSubCategoryBasicDetailsDto(ProductSubCategory productSubCategory);

    ProductSubCategoryDetailsDTO mapProductSubCategoryToProductSubCategoryDetailsDto(ProductSubCategory productSubCategory);

}
