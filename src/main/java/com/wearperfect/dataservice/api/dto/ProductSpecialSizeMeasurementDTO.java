package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ProductSpecialSizeMeasurementDTO {
    private Long id;
    private Long productSpecialSizeId;
    private Integer productMeasurementLabelId;
    private Byte productMeasurementUnitId;
    private Float value;
    private Boolean active = false;
    private Long createdBy;
    private Instant createdOn;
    private Long lastUpdatedBy;
    private Instant lastUpdatedOn;
}
