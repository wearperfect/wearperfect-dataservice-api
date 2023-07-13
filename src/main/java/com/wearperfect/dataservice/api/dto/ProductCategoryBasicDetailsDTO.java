package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCategoryBasicDetailsDTO {
    Integer id;
    String name;
    String desc;
    String thumbnail;
    String sourceLink;
    Integer sequence;
    Boolean active;
    CategoryBasicDetailsDTO category;
    private List<ProductCategoryMeasurementUnitBasicDetailsDTO> productCategoryMeasurementUnits;
}
