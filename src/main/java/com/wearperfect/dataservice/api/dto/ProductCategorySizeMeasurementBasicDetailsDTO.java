package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCategorySizeMeasurementBasicDetailsDTO {
    private Integer id;
    private Long productCategorySizeId;
    private Float value;
    private Float fromValue;
    private Float toValue;
    private Boolean ranged;
    private Boolean active;
    private ProductMeasurementLabelBasicDetailsDTO productMeasurementLabel;
    private MeasurementUnitBasicDetailsDTO measurementUnit;
}
