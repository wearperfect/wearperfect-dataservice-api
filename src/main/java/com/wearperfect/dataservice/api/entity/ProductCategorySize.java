package com.wearperfect.dataservice.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "product_category_sizes", schema = "wearperfect")
public class ProductCategorySize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "manufacturer_id", nullable = false)
    private Long manufacturerId;

    @NotNull
    @Column(name = "product_category_id", nullable = false)
    private Integer productCategoryId;

    @NotNull
    @Column(name = "gender_category_id", nullable = false)
    private Integer genderCategoryId;

    @Size(max = 200)
    @NotNull
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Size(max = 10)
    @NotNull
    @Column(name = "short_name", nullable = false, length = 10)
    private String shortName;

    @Size(max = 4096)
    @Column(name = "`desc`", length = 4096)
    private String desc;

    @Column(name = "us_size")
    private Double usSize;

    @Column(name = "uk_size")
    private Double ukSize;

    @Column(name = "centimeters")
    private Double centimeters;

    @Column(name = "inches")
    private Double inches;

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

}