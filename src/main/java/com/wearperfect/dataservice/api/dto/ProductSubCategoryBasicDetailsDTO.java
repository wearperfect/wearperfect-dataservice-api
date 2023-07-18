package com.wearperfect.dataservice.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ProductSubCategoryBasicDetailsDTO {
    Integer id;
    @NotNull
    @Size(max = 128)
    String name;
    @Size(max = 4096)
    String description;
    @Size(max = 1024)
    String thumbnail;
    @NotNull
    Integer sequence;
    @NotNull
    Boolean active;
    @NotNull
    ProductCategoryBasicDetailsDTO productCategory;
}
