package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ProductCategorySizeBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductCategorySizeDTO;
import com.wearperfect.dataservice.api.entity.ProductCategorySize;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = { UtilityMapper.class, ProductCategorySizeMeasurementMapper.class, SizeMapper.class, UserMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductCategorySizeMapper {

    ProductCategorySizeDTO mapProductCategorySizeToProductCategorySizeDto(ProductCategorySize productCategorySize);

    ProductCategorySize mapProductCategorySizeDtoToProductCategorySize(ProductCategorySizeDTO productCategorySizeDTO);

    ProductCategorySizeBasicDetailsDTO mapProductCategorySizeToProductCategorySizeBasicDetailsDto(ProductCategorySize productCategorySize);
}
