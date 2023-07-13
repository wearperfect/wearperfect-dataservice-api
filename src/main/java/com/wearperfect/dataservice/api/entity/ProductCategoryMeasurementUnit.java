package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "product_category_measurement_units")
public class ProductCategoryMeasurementUnit {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "product_category_id")
    Integer productCategoryId;

    @Column(name = "measurement_unit_id")
    Integer measurementUnitId;

    @Column(name = "created_by")
    Long createdBy;

    @Column(name = "created_on")
    Date createdOn;

    @Column(name = "last_updated_by")
    Long lastUpdatedBy;

    @Column(name = "last_updated_on")
    Date lastUpdatedOn;

    @Column(name = "active")
    Boolean active;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = ProductCategory.class)
    @JoinColumn(name="product_category_id", referencedColumnName = "id", insertable = false, updatable = false)
    ProductCategory productCategory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = MeasurementUnit.class)
    @JoinColumn(name="measurement_unit_id", referencedColumnName = "id", insertable = false, updatable = false)
    MeasurementUnit measurementUnit;
}
