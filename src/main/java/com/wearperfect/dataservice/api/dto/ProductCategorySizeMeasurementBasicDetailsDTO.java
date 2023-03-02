package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCategorySizeMeasurementBasicDetailsDTO {
    private Integer id;
    private Float value;
    private Boolean active;
    private ProductMeasurementLabelBasicDetailsDTO productMeasurementLabel;
}
