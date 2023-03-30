package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.UserDiscountCouponBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserDiscountCouponDTO;
import com.wearperfect.dataservice.api.entity.UserDiscountCoupon;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDiscountCouponMapper {
    UserDiscountCoupon mapUserDiscountCouponDtoToUserDiscountCoupon(UserDiscountCouponDTO userDiscountCouponDTO);

    UserDiscountCouponDTO mapUserDiscountCouponToUserDiscountCouponDto(UserDiscountCoupon userDiscountCoupon);

    UserDiscountCouponBasicDetailsDTO mapUserDiscountCouponToUserDiscountCouponBasicDetailsDTO(UserDiscountCoupon userDiscountCoupon);
}
