package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class WishlistCollectionDetailsDTO {
    private Long id;
    private String name;
    private Long userId;
    private Long coverWishlistProductId;
    private Long createdBy;
    private Long createdOn;
    private Long lastUpdatedBy;
    private Long lastUpdatedOn;
    private Boolean active = false;
    private WishlistProductDetailsDTO coverWishlistProduct;
}
