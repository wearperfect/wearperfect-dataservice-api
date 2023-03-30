package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDiscountCouponBasicDetailsDTO {
    private Long id;
    private Long productId;
    private Boolean active = false;
    private DiscountCouponBasicDetailsDTO discountCoupon;
}
