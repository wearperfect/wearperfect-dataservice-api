package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.UserDiscountCouponProductBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserDiscountCouponProductDTO;
import com.wearperfect.dataservice.api.entity.UserDiscountCouponProduct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDiscountCouponProductMapper {
    UserDiscountCouponProduct mapUserDiscountCouponProductDtoToUserDiscountCouponProduct(UserDiscountCouponProductDTO userDiscountCouponProductDTO);

    UserDiscountCouponProductDTO mapUserDiscountCouponProductToUserDiscountCouponProductDto(UserDiscountCouponProduct userDiscountCouponProduct);

    UserDiscountCouponProductBasicDetailsDTO mapUserDiscountCouponProductToUserDiscountCouponProductBasicDetailsDto(UserDiscountCouponProduct userDiscountCouponProduct);
}
