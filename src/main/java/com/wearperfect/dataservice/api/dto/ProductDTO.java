package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
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
    Integer currencyId;
    Long createdBy;
    Instant createdOn;
    Long lastUpdatedBy;
    Instant lastUpdatedOn;
    Boolean active;
}
