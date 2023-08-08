package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @jakarta.validation.constraints.Size(max = 256)
    @Column(name = "name", length = 256)
    private String name;

    @jakarta.validation.constraints.Size(max = 512)
    @Column(name = "description", length = 512)
    private String description;

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
    private Short primaryMeasurementUnitId;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id", insertable = false, updatable = false)
    User manufacturer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductCategory.class)
    @JoinColumn(name = "product_category_id", referencedColumnName = "id", insertable = false, updatable = false)
    ProductCategory productCategory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = GenderCategory.class)
    @JoinColumn(name = "gender_category_id", referencedColumnName = "id", insertable = false, updatable = false)
    GenderCategory genderCategory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = MeasurementUnit.class)
    @JoinColumn(name = "primary_measurement_unit_id", referencedColumnName = "id", insertable = false, updatable = false)
    MeasurementUnit primaryMeasurementUnit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
    @JoinColumn(name = "created_by", referencedColumnName = "id", insertable = false, updatable = false)
    User createdByUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
    @JoinColumn(name = "last_updated_by", referencedColumnName = "id", insertable = false, updatable = false)
    User lastUpdatedByUser;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "productCategorySizeChart", fetch = FetchType.LAZY, targetEntity = ProductCategorySize.class, cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductCategorySize> productCategorySizes;
}
