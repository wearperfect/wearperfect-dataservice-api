package com.wearperfect.dataservice.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "product_special_size_measurements")
public class ProductSpecialSizeMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "product_special_size_id", nullable = false)
    private Long productSpecialSizeId;

    @NotNull
    @Column(name = "product_measurement_label_id", nullable = false)
    private Integer productMeasurementLabelId;

    @Column(name = "product_measurement_unit_id")
    private Byte productMeasurementUnitId;

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

    @Column(name = "last_updated_on")
    private Instant lastUpdatedOn;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductSpecialSize.class)
    @JoinColumn(name = "product_special_size_id", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private ProductSpecialSize productSpecialSize;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductMeasurementLabel.class)
    @JoinColumn(name = "product_measurement_label_id", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private ProductMeasurementLabel productMeasurementLabel;
}