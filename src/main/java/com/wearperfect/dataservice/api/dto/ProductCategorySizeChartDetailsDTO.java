package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class ProductCategorySizeChartDetailsDTO {
    private Integer id;
    private String name;
    private String description;
    private Boolean active;
    private Instant createdOn;
    private Instant lastUpdatedOn;
    private UserBasicDetailsDTO manufacturer;
    private ProductCategoryBasicDetailsDTO productCategory;
    private GenderCategoryBasicDetailsDTO genderCategory;
    private List<ProductCategorySizeDTO> productCategorySizes;
    private MeasurementUnitBasicDetailsDTO primaryMeasurementUnit;
    private UserBasicDetailsDTO createdByUser;
    private UserBasicDetailsDTO lastUpdatedByUser;
}
