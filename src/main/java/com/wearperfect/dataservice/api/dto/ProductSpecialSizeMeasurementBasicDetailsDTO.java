package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSpecialSizeMeasurementBasicDetailsDTO {
    private Long id;
    private Long productSpecialSizeId;
    private Integer productMeasurementLabelId;
    private Byte productMeasurementUnitId;
    private Float value;
    private Boolean active;
    private ProductMeasurementLabelBasicDetailsDTO productMeasurementLabel;
}
