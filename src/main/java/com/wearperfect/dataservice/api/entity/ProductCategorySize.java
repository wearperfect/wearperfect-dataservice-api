package com.wearperfect.dataservice.api.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product_category_sizes")
public class ProductCategorySize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "product_category_size_chart_id", nullable = false)
    private Integer productCategorySizeChartId;

    @NotNull
    @Column(name = "size_id", nullable = false, length = 200)
    private Integer sizeId;

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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductCategorySizeChart.class)
    @JoinColumn(name = "product_category_size_chart_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ProductCategorySizeChart productCategorySizeChart;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Size.class)
    @JoinColumn(name = "size_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Size size;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "productCategorySize", fetch = FetchType.LAZY, targetEntity = ProductCategorySizeMeasurement.class, cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductCategorySizeMeasurement> productCategorySizeMeasurements;
}
