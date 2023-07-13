package com.wearperfect.dataservice.api.dto;

import com.wearperfect.dataservice.api.entity.MeasurementUnit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSpecialSizeMeasurementBasicDetailsDTO {
    private Long id;
    private Long productSpecialSizeId;
    private Float value;
    private Float fromValue;
    private Float toValue;
    private Boolean ranged;
    private Boolean active;
    private ProductMeasurementLabelBasicDetailsDTO productMeasurementLabel;
    private MeasurementUnitBasicDetailsDTO measurementUnit;
}
