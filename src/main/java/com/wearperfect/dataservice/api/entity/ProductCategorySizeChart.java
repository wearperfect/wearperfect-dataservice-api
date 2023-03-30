package com.wearperfect.dataservice.api.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product_category_size_charts")
public class ProductCategorySizeChart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "manufacturer_id", nullable = false)
    private Long manufacturerId;

    @NotNull
    @Column(name = "product_category_id", nullable = false)
    private Integer productCategoryId;

    @NotNull
    @Column(name = "gender_category_id", nullable = false)
    private Integer genderCategoryId;

    @jakarta.validation.constraints.Size(max = 1024)
    @Column(name = "desc", length = 4096)
    private String desc;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @NotNull
    @Column(name = "created_on", nullable = false)
    private Instant createdOn;

    @Column(name = "last_updated_on")
    private Instant lastUpdatedOn;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "last_updated_by")
    private Long lastUpdatedBy;

    @OneToMany(mappedBy = "productCategorySizeChart", fetch = FetchType.LAZY, targetEntity = ProductCategorySize.class, cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductCategorySize> productCategorySizes;
}
