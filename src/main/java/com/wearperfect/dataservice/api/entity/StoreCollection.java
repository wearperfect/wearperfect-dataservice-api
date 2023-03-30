package com.wearperfect.dataservice.api.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "store_collections")
public class StoreCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 64)
    @NotNull
    @Column(name = "title", nullable = false, length = 64)
    private String title;

    @Size(max = 512)
    @NotNull
    @Column(name = "`desc`", nullable = false, length = 512)
    private String desc;

    @Size(max = 512)
    @NotNull
    @Column(name = "cover", nullable = false, length = 1024)
    private String cover;

    @Size(max = 512)
    @NotNull
    @Column(name = "thumbnail", nullable = false, length = 1024)
    private String thumbnail;

    @Size(max = 32)
    @NotNull
    @Column(name = "action_text", nullable = false, length = 32)
    private String actionText;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @Column(name = "created_on")
    private Instant createdOn;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "last_updated_on")
    private Instant lastUpdatedOn;

    @Column(name = "last_updated_by")
    private Long lastUpdatedBy;

    @OneToMany(mappedBy = "storeCollection", fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = StoreCollectionProduct.class)
    List<StoreCollectionProduct> storeCollectionProducts;
}