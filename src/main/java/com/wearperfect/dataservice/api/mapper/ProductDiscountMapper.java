package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ProductDiscountBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductDiscountDTO;
import com.wearperfect.dataservice.api.entity.ProductDiscount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductDiscountMapper {

    ProductDiscount mapProductDiscountDtoToProductDiscount(ProductDiscountDTO productDiscountDTO);

    ProductDiscountDTO mapProductDiscountToProductDiscountDto(ProductDiscount productDiscount);

    ProductDiscountBasicDetailsDTO mapProductDiscountToProductDiscountBasicDetailsDto(ProductDiscount productDiscount);
}
