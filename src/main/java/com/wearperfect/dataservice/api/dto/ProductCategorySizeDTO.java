package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ProductCategorySizeDTO {
    private Long id;
    private Long manufacturerId;
    private Integer productCategoryId;
    private Integer genderCategoryId;
    private String size;
    private Double usSize;
    private Double ukSize;
    private String desc;
    private Boolean active;
    private Instant createdOn;
    private Instant lastUpdatedOn;
    private Long createdBy;
    private Long lastUpdatedBy;
    private ProductCategoryDTO productCategory;
    private GenderCategoryBasicDetailsDTO genderCategory;
    private UserBasicDetailsDTO manufacturer;
}
