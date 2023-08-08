package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductBasicDetailsDTO {
    Long id;
    String name;
    String description;
    String manufacturerName;
    Long manufacturerId;
    Boolean availableForSale;
    Boolean outOfStock = false;
    Float price;
    Boolean active;
    CurrencyBasicDetailsDTO currency;
    ProductMediaBasicDetailsDTO thumbnail;
    ProductDiscountBasicDetailsDTO productDiscount;
    Boolean productDiscountCouponsAvailable;
}
