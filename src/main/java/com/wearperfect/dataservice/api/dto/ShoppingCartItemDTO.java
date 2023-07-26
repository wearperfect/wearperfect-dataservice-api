package com.wearperfect.dataservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItemDTO {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer sizeId;
    private Short quantity;
    private Integer discountCouponId;
    private Long createdBy;
    private Instant createdOn;
    private Long lastUpdatedBy;
    private Instant lastUpdatedOn;
    private Boolean active;
    private ProductDetailsDTO product;
    private SizeBasicDetailsDTO size;
}
