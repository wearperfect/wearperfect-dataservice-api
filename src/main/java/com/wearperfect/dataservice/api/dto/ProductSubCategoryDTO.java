package com.wearperfect.dataservice.api.dto;

import com.wearperfect.dataservice.api.entity.ProductCategory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.wearperfect.dataservice.api.entity.ProductSubCategory}
 */
@Getter
@Setter
public class ProductSubCategoryDTO {
    Integer id;
    @NotNull
    @Size(max = 128)
    String name;
    @Size(max = 4096)
    String description;
    @Size(max = 1024)
    String thumbnail;
    @NotNull
    Integer productCategoryId;
    @NotNull
    Integer sequence;
    @NotNull
    Boolean active;
    @NotNull
    Instant createdOn;
    Instant lastUpdatedOn;
    @NotNull
    Long createdBy;
    Long lastUpdatedBy;
}