package com.wearperfect.dataservice.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ProductSubCategoryTagDTO {
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
}
