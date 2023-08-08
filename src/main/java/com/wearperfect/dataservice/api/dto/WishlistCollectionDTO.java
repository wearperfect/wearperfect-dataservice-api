package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class WishlistCollectionDTO {
    private Long id;
    private String name;
    private Long userId;
    private Long createdBy;
    private Instant createdOn;
    private Long lastUpdatedBy;
    private Instant lastUpdatedOn;
    private Boolean active = false;
}
