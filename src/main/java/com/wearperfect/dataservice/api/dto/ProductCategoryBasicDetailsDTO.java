package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

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
}
