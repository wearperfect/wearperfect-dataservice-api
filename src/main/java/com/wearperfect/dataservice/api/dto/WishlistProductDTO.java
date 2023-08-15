package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class WishlistProductDTO {
    private Long id;
    private Long productId;
    private Long userId;
    private Instant createdOn;
    private Long createdBy;
    private Instant lastUpdatedOn;
    private Long lastUpdatedBy;
    private Boolean active;
}
