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
@Table(name = "shopping_cart_items")
@EntityListeners(AuditingEntityListener.class)
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
    private Boolean active;

    @Fetch(value = FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Product.class)
    @JoinColumn(name="product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    @Fetch(value = FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Size.class)
    @JoinColumn(name = "size_id", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private Size size;
}
