package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCategoryMeasurementUnitBasicDetailsDTO {
    Integer id;
    Integer productCategoryId;
    Integer measurementUnitId;
    Boolean active;
    MeasurementUnitBasicDetailsDTO measurementUnit;
}
