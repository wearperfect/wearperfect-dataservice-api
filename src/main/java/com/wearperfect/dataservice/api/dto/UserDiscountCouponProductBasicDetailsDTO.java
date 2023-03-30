package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDiscountCouponProductBasicDetailsDTO {
    private Long id;
    private Boolean active = false;
    private UserDiscountCouponBasicDetailsDTO userDiscountCoupon;
    private ProductBasicDetailsDTO product;
}
