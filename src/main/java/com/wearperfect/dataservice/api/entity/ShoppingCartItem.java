package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "shopping_cart_items")
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @NotNull
    @Column(name = "size_id", nullable = false)
    private Integer sizeId;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Short quantity;

    @Column(name = "discount_coupon_id")
    private Integer discountCouponId;

    @NotNull
    @Column(name = "created_on", nullable = false)
    private Instant createdOn;

    @Column(name = "last_updated_on")
    private Instant lastUpdatedOn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Product.class)
    @JoinColumn(name="product_id", referencedColumnName = "id", insertable = false, updatable = false)
    Product product;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active;
}
