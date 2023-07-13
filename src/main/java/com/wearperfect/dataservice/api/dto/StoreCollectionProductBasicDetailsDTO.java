package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreCollectionProductBasicDetailsDTO {
    private Long id;
    private Boolean active = false;
    private ProductDetailsDTO product;
}
