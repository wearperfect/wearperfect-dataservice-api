package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ProductStyleDTO {
    private Long id;
    private Long productId;
    private Integer styleId;
    private Boolean active = false;
    private Long createdBy;
    private Instant createdOn;
    private StyleBasicDetailsDTO style;
}
