package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ProductSpecialSizeBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductSpecialSizeDTO;
import com.wearperfect.dataservice.api.entity.ProductSpecialSize;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = { UtilityMapper.class, SizeMapper.class, ProductSpecialSizeMeasurementMapper.class, UserMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductSpecialSizeMapper {

    ProductSpecialSize mapProductSpecialSizeDtoToProductSpecialSize(ProductSpecialSizeDTO productSpecialSizeDTO);

    ProductSpecialSizeDTO mapProductSpecialSizeToProductSpecialSizeDto(ProductSpecialSize productSpecialSize);

    ProductSpecialSizeBasicDetailsDTO mapProductSpecialSizeToProductSpecialSizeBasicDetailsDTO(ProductSpecialSize productSpecialSize);
}
