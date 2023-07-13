package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class ProductSpecialSizeChartDetailsDTO {
    private Long id;
    private Long productId;
    private String desc;
    private Boolean active = false;
    private Instant createdOn;
    private Instant lastUpdatedOn;
    private UserBasicDetailsDTO manufacturer;
    private ProductCategoryBasicDetailsDTO productCategory;
    private GenderCategoryBasicDetailsDTO genderCategory;
    private List<ProductSpecialSizeBasicDetailsDTO> productSpecialSizes;
    private MeasurementUnitBasicDetailsDTO primaryMeasurementUnit;
    private UserBasicDetailsDTO createdByUser;
    private UserBasicDetailsDTO lastUpdatedByUser;
}
