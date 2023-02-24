package com.wearperfect.dataservice.api.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "product_sizes", schema = "wearperfect")
public class ProductSize {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

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
    @Size(max = 16)
    @Column(name = "size", nullable = false, length = 200)
    private String size;

    @Size(max = 16)
    @Column(name = "us_size")
    private Double usSize;

    @Size(max = 16)
    @Column(name = "uk_size")
    private Double ukSize;

    @Size(max = 4096)
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

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "last_updated_by")
    private Long lastUpdatedBy;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Product.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
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
}