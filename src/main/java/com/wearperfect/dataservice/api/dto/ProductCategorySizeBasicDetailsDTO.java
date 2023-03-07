package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCategorySizeBasicDetailsDTO {
    private Long id;
    private Integer productCategorySizeChartId;
    private String desc;
    private Boolean active;
    private SizeBasicDetailsDTO size;
    private List<ProductCategorySizeMeasurementBasicDetailsDTO> productCategorySizeMeasurements;
}
