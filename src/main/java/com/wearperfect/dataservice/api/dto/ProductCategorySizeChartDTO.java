package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class ProductCategorySizeChartDTO {
    private Integer id;
    private String name;
    private String description;
    private Long manufacturerId;
    private Integer productCategoryId;
    private Integer genderCategoryId;
    private Short primaryMeasurementUnitId;
    private Boolean active;
    private Instant createdOn;
    private Instant lastUpdatedOn;
    private Long createdBy;
    private Long lastUpdatedBy;
    private List<ProductCategorySizeDTO> productCategorySizes;
}
