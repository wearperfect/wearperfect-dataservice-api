package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "product_inventory_items", schema = "wearperfect")
public class ProductInventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @NotNull
    @Column(name = "size_id", nullable = false)
    private Integer sizeId;

    @NotNull
    @Column(name = "warehouse_id", nullable = false)
    private Integer warehouseId;

    @NotNull
    @Column(name = "quantity_in_stock", nullable = false)
    private Integer quantityInStock;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Product.class)
    @JoinColumn(name="product_id", referencedColumnName = "id", insertable = false, updatable = false)
    Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Size.class)
    @JoinColumn(name="size_id", referencedColumnName = "id", insertable = false, updatable = false)
    Size size;
}