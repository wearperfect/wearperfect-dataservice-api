package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSizeBasicDetailsDTO {
    private Long id;
    private Long productId;
    private String name;
    private String shortName;
    private String desc;
    private Double usSize;
    private Double ukSize;
    private Boolean active;
    private GenderCategoryBasicDetailsDTO genderCategory;
    private ProductCategoryDTO productCategory;
}
