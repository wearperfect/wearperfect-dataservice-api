package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ProductInventoryItemDTO {
    private Long id;
    private Long productId;
    private Integer sizeId;
    private Integer warehouseId;
    private Integer quantityInStock;
    private Boolean active;
    private Long createdBy;
    private Instant createdOn;
    private Long lastUpdatedBy;
    private Instant lastUpdatedOn;
}
