package com.wearperfect.dataservice.api.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "product_measurement_labels")
public class ProductMeasurementLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 128)
    @NotNull
    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @NotNull
    @Column(name = "sequence", nullable = false)
    private Short sequence;

    @NotNull
    @Column(name = "product_category_id", nullable = false)
    private Integer productCategoryId;

    @NotNull
    @Column(name = "gender_category_id", nullable = false)
    private Integer genderCategoryId;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @NotNull
    @Column(name = "created_on", nullable = false)
    private Instant createdOn;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "last_updated_on")
    private Instant lastUpdatedOn;

    @Column(name = "last_updated_by")
    private Long lastUpdatedBy;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductCategory.class)
    @JoinColumn(name = "product_category_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    private ProductCategory productCategory;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = GenderCategory.class)
    @JoinColumn(name = "gender_category_id", referencedColumnName = "id",insertable = false, updatable = false, nullable = false)
    private GenderCategory genderCategory;
}