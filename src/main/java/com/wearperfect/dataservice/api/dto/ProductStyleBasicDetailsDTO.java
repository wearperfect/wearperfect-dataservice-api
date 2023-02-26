package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductStyleBasicDetailsDTO {
    private Long id;
    private Long productId;
    private Boolean active;
    private StyleBasicDetailsDTO style;
}
