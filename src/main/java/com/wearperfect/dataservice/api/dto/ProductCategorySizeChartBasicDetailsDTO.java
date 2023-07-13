package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCategorySizeChartBasicDetailsDTO {
    private Integer id;
    private String desc;
    private Boolean active;
    private List<ProductCategorySizeBasicDetailsDTO> productCategorySizes;
    private MeasurementUnitBasicDetailsDTO primaryMeasurementUnit;
}
