package com.wearperfect.dataservice.api.dto;

import com.wearperfect.dataservice.api.entity.Product;
import com.wearperfect.dataservice.api.entity.ProductSubCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.wearperfect.dataservice.api.entity.ProductSubCategoryTag}
 */
@Getter
@Setter
public class ProductSubCategoryTagDetailsDTO {
    Long id;
    @NotNull
    Long productId;
    @NotNull
    Integer productSubCategoryId;
    @NotNull
    Boolean active;
    @NotNull
    Instant createdOn;
    Instant lastUpdatedOn;
    @NotNull
    Long createdBy;
    Long lastUpdatedBy;
    @NotNull
    Product product;
    @NotNull
    ProductSubCategory productSubCategory;
}