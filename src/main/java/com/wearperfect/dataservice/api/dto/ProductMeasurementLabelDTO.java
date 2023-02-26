package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ProductMeasurementLabelDTO {
    private Integer id;
    private String name;
    private Short sequence;
    private Integer productCategoryId;
    private Integer genderCategoryId;
    private Boolean active;
    private Instant createdOn;
    private Long createdBy;
    private Instant lastUpdatedOn;
    private Long lastUpdatedBy;
    private ProductCategoryDTO productCategory;
    private GenderCategoryBasicDetailsDTO genderCategory;
}
