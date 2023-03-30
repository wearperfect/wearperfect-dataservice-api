package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDiscountBasicDetailsDTO {
    private Long id;
    private Long productId;
    private Float value;
    private Boolean active;
    private DiscountTypeBasicDetailsDTO discountType;
}
