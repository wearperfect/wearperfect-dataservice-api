package com.wearperfect.dataservice.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO {
    Long id;
    String title;
    String description;
    String features;
    Long manufacturedByUserId;
    Integer productCategoryId;
    Integer genderCategoryId;
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
