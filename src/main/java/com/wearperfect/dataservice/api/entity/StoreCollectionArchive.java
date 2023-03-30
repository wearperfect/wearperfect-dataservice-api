package com.wearperfect.dataservice.api.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "store_collection_archives")
public class StoreCollectionArchive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "store_collection_id", nullable = false)
    private Integer storeCollectionId;

    @Size(max = 64)
    @NotNull
    @Column(name = "title", nullable = false, length = 64)
    private String title;

    @Size(max = 512)
    @NotNull
    @Column(name = "`desc`", nullable = false, length = 512)
    private String desc;

    @Size(max = 1024)
    @NotNull
    @Column(name = "banner", nullable = false, length = 1024)
    private String banner;

    @Size(max = 1024)
    @NotNull
    @Column(name = "thumbnail", nullable = false, length = 1024)
    private String thumbnail;

    @Size(max = 32)
    @NotNull
    @Column(name = "action", nullable = false, length = 32)
    private String action;

    @NotNull
    @Column(name = "sequence_id", nullable = false)
    private Byte sequenceId;

    @NotNull
    @Column(name = "featured", nullable = false)
    private Boolean featured = false;

    @Column(name = "featured_by")
    private Long featuredBy;

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
}