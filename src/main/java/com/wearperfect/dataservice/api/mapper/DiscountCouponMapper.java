package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.DiscountCouponBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.DiscountCouponDTO;
import com.wearperfect.dataservice.api.entity.DiscountCoupon;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiscountCouponMapper {

    DiscountCoupon mapDiscountCouponDtoToDiscountCoupon(DiscountCouponDTO discountCouponDTO);

    DiscountCouponDTO mapDiscountCouponToDiscountCouponDto(DiscountCoupon discountCoupon);

    DiscountCouponBasicDetailsDTO mapDiscountCouponToDiscountCouponBasicDetailsDto(DiscountCoupon discountCoupon);
}
