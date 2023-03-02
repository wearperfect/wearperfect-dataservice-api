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
@Table(name = "product_special_sizes")
public class ProductSpecialSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "size_id", nullable = false)
    private Short sizeId;

    @NotNull
    @Column(name = "product_special_size_chart_id", nullable = false)
    private Long productSpecialSizeChartId;

    @javax.validation.constraints.Size(max = 512)
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
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Size.class)
    @JoinColumn(name = "size_id", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private Size size;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductSpecialSizeChart.class)
    @JoinColumn(name = "product_special_size_chart_id", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private ProductSpecialSizeChart productSpecialSizeChart;

    @OneToMany(mappedBy = "productSpecialSize", fetch = FetchType.LAZY, targetEntity = ProductSpecialSizeMeasurement.class, cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProductSpecialSizeMeasurement> productSpecialSizeMeasurements;
}