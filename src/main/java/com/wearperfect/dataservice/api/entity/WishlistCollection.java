package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "wishlist_collections")
@EntityListeners(AuditingEntityListener.class)
public class WishlistCollection extends Auditable {
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

    @Column(name = "cover_wishlist_collection_product_id", nullable = false)
    private Long coverWishlistCollectionProductId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = WishlistProduct.class)
    @JoinColumn(name = "cover_wishlist_collection_product_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private WishlistCollectionProduct coverWishlistCollectionProduct;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.LAZY, targetEntity = WishlistCollectionProduct.class)
    @JoinColumn(name="wishlist_collection_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<WishlistCollectionProduct> wishlistCollectionProducts;
}