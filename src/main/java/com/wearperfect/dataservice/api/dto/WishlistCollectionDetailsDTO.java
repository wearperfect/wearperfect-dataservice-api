package com.wearperfect.dataservice.api.dto;

import com.wearperfect.dataservice.api.entity.WishlistCollectionProduct;
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
    private Long createdBy;
    private Instant createdOn;
    private Long lastUpdatedBy;
    private Instant lastUpdatedOn;
    private Boolean active = false;
    private List<WishlistCollectionProductDetailsDTO> wishlistCollectionProducts;
}
