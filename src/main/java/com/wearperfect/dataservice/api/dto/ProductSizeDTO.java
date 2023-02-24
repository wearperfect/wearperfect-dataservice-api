package com.wearperfect.dataservice.api.dto;

import com.wearperfect.dataservice.api.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ProductSizeDTO {
    private Long id;
    private Long productId;
    private Long manufacturerId;
    private Integer productCategoryId;
    private Integer genderCategoryId;
    private String size;
    private Double usSize;
    private Double ukSize;
    private String desc;
    private Boolean active = false;
    private Instant createdOn;
    private Instant lastUpdatedOn;
    private Long createdBy;
    private Long lastUpdatedBy;
}
