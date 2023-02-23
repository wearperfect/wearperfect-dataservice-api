package com.wearperfect.dataservice.api.dto;

import com.wearperfect.dataservice.api.entity.ProductStyle;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    Long id;
    String name;
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
    Long createdOn;
    Long lastUpdatedBy;
    Long lastUpdatedOn;
    Boolean active;
    UserDTO manufacturedByUser;
    ProductCategoryDTO productCategory;
    GenderCategoryDTO genderCategory;
    ColorBasicDetailsDTO color;
    List<ProductMediaDTO> productMediaList;
    List<ProductStyleDTO> productStyleList;
}
