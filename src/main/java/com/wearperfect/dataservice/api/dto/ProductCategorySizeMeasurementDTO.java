package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ProductCategorySizeMeasurementDTO {
    private Integer id;
    private Long productCategorySizeId;
    private Integer productMeasurementLabelId;
    private Byte measurementUnitId;
    private Float value;
    private Boolean active;
    private Long createdBy;
    private Instant createdOn;
    private Long lastUpdatedBy;
    private Instant lastUpdatedOn;
    private ProductMeasurementLabelDTO productMeasurementLabel;
    private MeasurementUnitBasicDetailsDTO measurementUnit;
}
