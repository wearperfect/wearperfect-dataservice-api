package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishlistCollectionDTO extends AuditableDTO {
    private Long id;
    private String name;
    private Long userId;
    private Long coverWishlistCollectionProductId;
}
