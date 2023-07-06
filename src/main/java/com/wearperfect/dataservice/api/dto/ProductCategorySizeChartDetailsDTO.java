package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class ProductCategorySizeChartDetailsDTO {
    private Integer id;
    private Long manufacturerId;
    private Integer productCategoryId;
    private Integer genderCategoryId;
    private String desc;
    private Boolean active;
    private Instant createdOn;
    private Instant lastUpdatedOn;
    private Long createdBy;
    private Long lastUpdatedBy;
    private UserBasicDetailsDTO manufacturer;
    private ProductCategoryBasicDetailsDTO productCategory;
    private GenderCategoryBasicDetailsDTO genderCategory;
    private List<ProductCategorySizeDTO> productCategorySizes;
    private UserBasicDetailsDTO createdByUser;
    private UserBasicDetailsDTO lastUpdatedByUser;
}
