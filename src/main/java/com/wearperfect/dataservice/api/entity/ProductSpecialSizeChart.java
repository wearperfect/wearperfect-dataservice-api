package com.wearperfect.dataservice.api.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product_special_size_charts")
public class ProductSpecialSizeChart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @NotNull
    @Column(name = "manufacturer_id", nullable = false)
    private Long manufacturerId;

    @NotNull
    @Column(name = "product_category_id", nullable = false)
    private Integer productCategoryId;

    @NotNull
    @Column(name = "gender_category_id", nullable = false)
    private Integer genderCategoryId;

    @NotNull
    @Column(name = "primary_measurement_unit_id", nullable = false)
    private Long primaryMeasurementUnitId;

    @Size(max = 512)
    @Column(name = "`desc`", length = 512)
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

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Product.class)
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
    @JoinColumn(name="manufacturer_id", referencedColumnName = "id", insertable = false, updatable = false)
    User manufacturer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductCategory.class)
    @JoinColumn(name="product_category_id", referencedColumnName = "id", insertable = false, updatable = false)
    ProductCategory productCategory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = GenderCategory.class)
    @JoinColumn(name="gender_category_id", referencedColumnName = "id", insertable = false, updatable = false)
    GenderCategory genderCategory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = MeasurementUnit.class)
    @JoinColumn(name="primary_measurement_unit_id", referencedColumnName = "id", insertable = false, updatable = false)
    MeasurementUnit primaryMeasurementUnit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
    @JoinColumn(name="created_by", referencedColumnName = "id", insertable = false, updatable = false)
    User createdByUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
    @JoinColumn(name="last_updated_by", referencedColumnName = "id", insertable = false, updatable = false)
    User lastUpdatedByUser;

    @OneToMany(mappedBy = "productSpecialSizeChart", fetch = FetchType.LAZY, targetEntity = ProductSpecialSize.class, cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductSpecialSize> productSpecialSizes;
}