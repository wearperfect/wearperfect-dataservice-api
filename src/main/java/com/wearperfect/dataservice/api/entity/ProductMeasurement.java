package com.wearperfect.dataservice.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "product_measurements")
public class ProductMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "product_size_id")
    private Long productSizeId;

    @Column(name = "product_measurement_label_id", nullable = false)
    private Integer productMeasurementLabelId;

    @NotNull
    @Column(name = "product_measurement_unit_id", nullable = false)
    private Integer productMeasurementUnitId;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductSize.class)
    @JoinColumn(name = "product_size_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    private ProductSize productSize;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductMeasurementLabel.class)
    @JoinColumn(name = "product_measurement_label_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    private ProductMeasurementLabel productMeasurementLabel;
}