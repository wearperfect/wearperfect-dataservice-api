package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDiscountCouponBasicDetailsDTO {
    private Long id;
    private Boolean active = false;
    private UserBasicDetailsDTO user;
    private DiscountCouponBasicDetailsDTO discountCoupon;
}
