package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishlistCollectionProductDetailsDTO {
    Long id;
    Long productId;
    String productName;
    String productDescription;
    Long productManufacturerId;
    String productManufacturerName;
    Boolean productAvailableForSale;
    Boolean productOutOfStock;
    Float productPrice;
    Integer productPriceCurrencyId;
    String productPriceCurrencyName;
    String productPriceCurrencyShortName;
    Integer productPriceCurrencyCountryId;
    String productThumbnail;
    String productThumbnailTitle;
    String productThumbnailDescription;
    Integer productThumbnailContentTypeId;
    String productThumbnailContentTypeExtension;
    String productThumbnailContentType;
    Float productDiscountValue;
    String productDiscountTypeName;
    Boolean productDiscountActive;
    Boolean productDiscountCouponsAvailable;
    Boolean active;
}
