package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class ProductSizeDTO {
    private Long id;
    private Long manufacturerId;
    private Integer productCategoryId;
    private Integer genderCategoryId;
    private Integer sizeId;
    private String desc;
    private Boolean active;
    private Instant createdOn;
    private Instant lastUpdatedOn;
    private Long createdBy;
    private Long lastUpdatedBy;
    private ProductCategoryDTO productCategory;
    private GenderCategoryBasicDetailsDTO genderCategory;
    private UserBasicDetailsDTO manufacturer;
    private SizeDTO size;
    private List<ProductMeasurementDTO> productMeasurements;
}
