package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishlistCollectionDetailsDTO extends AuditableDTO {
    private Long id;
    private String name;
    private Long userId;
    private Long coverWishlistCollectionProductId;
    private WishlistCollectionProductDetailsDTO coverWishlistCollectionProduct;
}
