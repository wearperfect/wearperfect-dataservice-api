package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ProductDiscountCouponBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductDiscountCouponDTO;
import com.wearperfect.dataservice.api.entity.ProductDiscountCoupon;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductDiscountCouponMapper {

    ProductDiscountCoupon mapProductDiscountCouponDtoToProductDiscountCoupon(ProductDiscountCouponDTO productDiscountCouponDTO);

    ProductDiscountCouponDTO mapProductDiscountCouponToProductDiscountCouponDto(ProductDiscountCoupon productDiscountCoupon);

    ProductDiscountCouponBasicDetailsDTO mapProductDiscountCouponToProductDiscountCouponBasicDetailsDto(ProductDiscountCoupon productDiscountCoupon);
}
