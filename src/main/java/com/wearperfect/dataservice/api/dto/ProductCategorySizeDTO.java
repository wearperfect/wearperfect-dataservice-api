package com.wearperfect.dataservice.api.dto;

import com.wearperfect.dataservice.api.entity.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class ProductCategorySizeDTO {
    private Long id;
    private Integer productCategorySizeChartId;
    private Integer sizeId;
    private String desc;
    private Boolean active;
    private Instant createdOn;
    private Instant lastUpdatedOn;
    private Long createdBy;
    private Long lastUpdatedBy;
    private Size size;
    private List<ProductMeasurementDTO> productMeasurements;
}
