package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "wishlist_collection_products")
public class WishlistCollectionProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "wishlist_collection_id", nullable = false)
    private Long wishlistCollectionId;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @NotNull
    @Column(name = "created_on", nullable = false)
    private Instant createdOn;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "last_updated_on")
    private Instant lastUpdatedOn;

    @Column(name = "last_updated_by")
    private Long lastUpdatedBy;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
    private Product product;
}