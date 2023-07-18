package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class ProductDetailsDTO {
    Long id;
    String name;
    String description;
    String features;
    Boolean availableForSale;
    Float price;
    Integer currencyId;
    Instant createdOn;
    Instant lastUpdatedOn;
    Boolean active;
    CurrencyBasicDetailsDTO currency;
    UserBasicDetailsDTO manufacturer;
    ProductCategoryBasicDetailsDTO productCategory;
    GenderCategoryBasicDetailsDTO genderCategory;
    ColorBasicDetailsDTO color;
    List<ProductMediaBasicDetailsDTO> productMediaList;
    List<ProductStyleBasicDetailsDTO> productStyles;
    List<ProductInventoryItemBasicDetailsDTO> productInventoryItems;
    ProductCategorySizeChartBasicDetailsDTO productCategorySizeChart;
    ProductDiscountBasicDetailsDTO productDiscount;
    List<ProductDiscountCouponBasicDetailsDTO> productDiscountCoupons;
}
