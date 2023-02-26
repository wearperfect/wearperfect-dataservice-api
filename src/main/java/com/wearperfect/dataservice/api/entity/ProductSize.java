package com.wearperfect.dataservice.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product_sizes")
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "product_id", nullable = false, length = 200)
    private Integer productId;

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
    @Column(name = "size_id", nullable = false, length = 200)
    private Integer sizeId;

    @javax.validation.constraints.Size(max = 1024)
    @Column(name = "`desc`", length = 4096)
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
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Product.class)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Product product;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductCategory.class)
    @JoinColumn(name = "product_category_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ProductCategory productCategory;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = GenderCategory.class)
    @JoinColumn(name = "gender_category_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private GenderCategory genderCategory;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private User manufacturer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Size.class)
    @JoinColumn(name = "size_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Size size;

    @OneToMany(mappedBy = "productSize", fetch = FetchType.LAZY, targetEntity = ProductMeasurement.class, cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductMeasurement> productMeasurements;
}