package com.wearperfect.dataservice.api.dto;

import com.wearperfect.dataservice.api.entity.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCategorySizeBasicDetailsDTO {
    private Long id;
    private String desc;
    private Boolean active;
    private Size size;
    private List<ProductMeasurementBasicDetailsDTO> productMeasurements;
}
