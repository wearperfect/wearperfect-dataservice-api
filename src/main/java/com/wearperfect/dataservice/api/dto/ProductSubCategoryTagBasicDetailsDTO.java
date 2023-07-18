package com.wearperfect.dataservice.api.dto;

import com.wearperfect.dataservice.api.entity.Product;
import com.wearperfect.dataservice.api.entity.ProductSubCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ProductSubCategoryTagBasicDetailsDTO {
    Long id;
    @NotNull
    Boolean active;
    @NotNull
    Product product;
    @NotNull
    ProductSubCategory productSubCategory;
}
