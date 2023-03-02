package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInventoryItemBasicDetailsDTO {
    private Long id;
    private Long productId;
    private Integer sizeId;
    private Integer warehouseId;
    private Integer quantityInStock;
    private Boolean active;
    private SizeBasicDetailsDTO size;
}
