package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductDTO {
    Long id;
    String name;
    String description;
    String features;
    Long manufacturerId;
    Integer productCategoryId;
    Integer genderCategoryId;
    Integer colorId;
    Boolean availableForSale;
    Float price;
    Integer discount;
    Integer discountTypeId;
    Integer currencyId;
    Long createdBy;
    Date createdOn;
    Long lastUpdatedBy;
    Date lastUpdatedOn;
    Boolean active;
}
