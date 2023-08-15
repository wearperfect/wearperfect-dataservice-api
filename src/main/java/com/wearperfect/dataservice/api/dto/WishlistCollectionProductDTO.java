package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class WishlistCollectionProductDTO {
    private Long id;
    private Long wishlistCollectionId;
    private Long wishlistProductId;
    private Instant createdOn;
    private Long createdBy;
    private Instant lastUpdatedOn;
    private Long lastUpdatedBy;
    private Boolean active = true;
}
