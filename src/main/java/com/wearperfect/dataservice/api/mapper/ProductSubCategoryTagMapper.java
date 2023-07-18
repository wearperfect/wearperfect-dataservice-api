package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ProductSubCategoryTagBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductSubCategoryTagDTO;
import com.wearperfect.dataservice.api.dto.ProductSubCategoryTagDetailsDTO;
import com.wearperfect.dataservice.api.entity.ProductSubCategoryTag;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = { UtilityMapper.class, ProductSubCategoryMapper.class, ProductMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductSubCategoryTagMapper {

    ProductSubCategoryTag mapProductSubCategoryTagDtoToProductSubCategoryTag(ProductSubCategoryTagDTO productSubCategoryTagDto);

    ProductSubCategoryTagDTO mapProductSubCategoryTagToProductSubCategoryTagDto(ProductSubCategoryTag productSubCategoryTag);

    ProductSubCategoryTagBasicDetailsDTO mapProductSubCategoryTagToProductSubCategoryTagBasicDetailsDto(ProductSubCategoryTag productSubCategoryTag);

    ProductSubCategoryTagDetailsDTO mapProductSubCategoryTagToProductSubCategoryTagDetailsDto(ProductSubCategoryTag productSubCategoryTag);
}
