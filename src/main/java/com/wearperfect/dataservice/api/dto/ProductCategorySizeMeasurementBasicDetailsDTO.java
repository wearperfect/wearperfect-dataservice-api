package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCategorySizeMeasurementBasicDetailsDTO {
    private Integer id;
    private Long productCategorySizeId;
    private Float value;
    private Boolean active;
    private ProductMeasurementLabelBasicDetailsDTO productMeasurementLabel;
    private MeasurementUnitBasicDetailsDTO measurementUnit;
}
