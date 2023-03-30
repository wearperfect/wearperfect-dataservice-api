package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class DiscountCouponDTO {
    private Long id;
    private String name;
    private String desc;
    private Float value;
    private Byte discountTypeId;
    private Instant activationDate;
    private Instant expirationDate;
    private Boolean active = false;
    private Long createdBy;
    private Instant createdOn;
    private Long lastUpdatedBy;
    private Instant lastUpdatedOn;
}
