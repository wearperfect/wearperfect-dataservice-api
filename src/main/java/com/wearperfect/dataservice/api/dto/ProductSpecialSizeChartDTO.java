package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ProductSpecialSizeChartDTO {
    private Long id;
    private Long productId;
    private Short primaryMeasurementUnitId;
    private String desc;
    private Boolean active = false;
    private Instant createdOn;
    private Instant lastUpdatedOn;
    private Long createdBy;
    private Long lastUpdatedBy;
}
