package com.wearperfect.dataservice.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "product_category_size_measurements")
public class ProductCategorySizeMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "product_category_size_id")
    private Long productCategorySizeId;

    @Column(name = "product_measurement_label_id", nullable = false)
    private Integer productMeasurementLabelId;

    @NotNull
    @Column(name = "measurement_unit_id", nullable = false)
    private Byte measurementUnitId;

    @NotNull
    @Column(name = "value", nullable = false)
    private Float value;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @NotNull
    @Column(name = "created_on", nullable = false)
    private Instant createdOn;

    @Column(name = "last_updated_by")
    private Long lastUpdatedBy;

    @NotNull
    @Column(name = "last_updated_on", nullable = false)
    private Instant lastUpdatedOn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductCategorySize.class)
    @JoinColumn(name = "product_category_size_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    @JsonIgnore
    private ProductCategorySize productCategorySize;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductMeasurementLabel.class)
    @JoinColumn(name = "product_measurement_label_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    @JsonIgnore
    private ProductMeasurementLabel productMeasurementLabel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = MeasurementUnit.class)
    @JoinColumn(name = "measurement_unit_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    @JsonIgnore
    private MeasurementUnit measurementUnit;
}