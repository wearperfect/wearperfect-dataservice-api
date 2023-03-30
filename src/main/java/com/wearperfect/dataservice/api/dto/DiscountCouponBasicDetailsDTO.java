package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class DiscountCouponBasicDetailsDTO {
    private Long id;
    private String name;
    private String desc;
    private Float value;
    private Instant activationDate;
    private Instant expirationDate;
    private Boolean active = false;
    private DiscountTypeBasicDetailsDTO discountType;
}
