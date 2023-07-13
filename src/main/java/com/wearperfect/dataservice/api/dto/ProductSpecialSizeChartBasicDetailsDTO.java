package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductSpecialSizeChartBasicDetailsDTO {
    private Long id;
    private Long productId;
    private String desc;
    private Boolean active;
    private List<ProductSpecialSizeBasicDetailsDTO> productSpecialSizes;
    private MeasurementUnitBasicDetailsDTO primaryMeasurementUnit;
}
