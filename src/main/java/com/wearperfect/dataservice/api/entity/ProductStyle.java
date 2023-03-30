package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product_styles", schema = "wearperfect")
public class ProductStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @NotNull
    @Column(name = "style_id", nullable = false)
    private Integer styleId;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Size(max = 45)
    @NotNull
    @Column(name = "created_on", nullable = false, length = 45)
    private Instant createdOn;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Product.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Style.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "style_id", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private Style style;

//    @OneToMany(mappedBy = "productStyle", fetch = FetchType.LAZY, targetEntity = RegionStyle.class, cascade = CascadeType.ALL, orphanRemoval = true)
//    List<RegionStyle> regionStyles;
}