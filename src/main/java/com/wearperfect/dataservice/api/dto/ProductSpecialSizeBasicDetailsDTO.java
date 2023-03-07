package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductSpecialSizeBasicDetailsDTO {
    private Long id;
    private Long productSpecialSizeChartId;
    private String desc;
    private Boolean active;
    private SizeBasicDetailsDTO size;
    private List<ProductSpecialSizeMeasurementBasicDetailsDTO> productSpecialSizeMeasurements;
}
