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
    Boolean availableForSale;
    Float price;
    Boolean active;
    CurrencyBasicDetailsDTO currency;
    UserBasicDetailsDTO manufacturer;
    List<ProductMediaBasicDetailsDTO> productMediaList;
    List<ProductInventoryItemBasicDetailsDTO> productInventoryItems;
    ProductDiscountBasicDetailsDTO productDiscount;
    List<ProductDiscountCouponBasicDetailsDTO> productDiscountCoupons;
}
