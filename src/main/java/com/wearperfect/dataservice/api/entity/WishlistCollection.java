package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "wishlist_collections")
@EntityListeners(AuditingEntityListener.class)
public class WishlistCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 64)
    @NotNull
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull
    @CreatedBy
    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @NotNull
    @CreatedDate
    @Column(name = "created_on", nullable = false)
    private Instant createdOn;

    @LastModifiedBy
    @Column(name = "last_updated_by")
    private Long lastUpdatedBy;

    @LastModifiedDate
    @Column(name = "last_updated_on")
    private Instant lastUpdatedOn;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Fetch(FetchMode.JOIN)
    @OneToMany(fetch = FetchType.LAZY, targetEntity = WishlistCollectionProduct.class)
    @JoinColumn(name="wishlist_collection_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<WishlistCollectionProduct> wishlistCollectionProducts;

}