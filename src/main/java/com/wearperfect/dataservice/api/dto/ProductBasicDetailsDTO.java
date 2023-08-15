package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ProductBasicDetailsDTO {
    Long id;
    String name;
    String description;
    Long manufacturerId;
    String manufacturerName;
    Boolean availableForSale;
    Boolean outOfStock;
    Float price;
    Integer priceCurrencyId;
    String priceCurrencyName;
    String priceCurrencyShortName;
    Integer priceCurrencyCountryId;
    String thumbnail;
    String thumbnailTitle;
    String thumbnailDescription;
    Integer thumbnailContentTypeId;
    String thumbnailContentTypeExtension;
    String thumbnailContentType;
    Float productDiscountValue;
    String productDiscountTypeName;
    Boolean productDiscountActive;
    Boolean productDiscountCouponsAvailable;
    Instant createdOn;
    Long createdBy;
    Instant lastUpdatedOn;
    Long lastUpdatedBy;
    Boolean active;
}
