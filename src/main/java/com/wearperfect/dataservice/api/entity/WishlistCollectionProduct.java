package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "wishlist_collection_products")
@EntityListeners(AuditingEntityListener.class)
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
    @CreatedDate
    @Column(name = "created_on", nullable = false)
    private Instant createdOn;

    @NotNull
    @CreatedBy
    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @LastModifiedDate
    @Column(name = "last_updated_on")
    private Instant lastUpdatedOn;

    @LastModifiedBy
    @Column(name = "last_updated_by")
    private Long lastUpdatedBy;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
    private Product product;
}