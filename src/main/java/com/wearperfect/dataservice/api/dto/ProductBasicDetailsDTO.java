package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ProductBasicDetailsDTO {
    Long id;
    String name;
    String description;
    String features;
    Boolean availableForSale;
    Float price;
    Integer discount;
    Integer discountTypeId;
    Integer currencyId;
    Date createdOn;
    Date lastUpdatedOn;
    Boolean active;
    UserBasicDetailsDTO manufacturer;
    ProductCategoryBasicDetailsDTO productCategory;
    GenderCategoryBasicDetailsDTO genderCategory;
    ColorBasicDetailsDTO color;
    List<ProductMediaBasicDetailsDTO> productMediaList;
    List<ProductStyleDTO> productStyleList;
    ProductCategorySizeChartBasicDetailsDTO productCategorySizeChart;
}
