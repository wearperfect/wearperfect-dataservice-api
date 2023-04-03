package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "region_styles", schema = "wearperfect")
public class RegionStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "style_id", nullable = false)
    private Integer styleId;

    @Column(name = "region_id", nullable = false)
    private Integer regionId;

    @Size(max = 256)
    @Column(name = "name", length = 256)
    private String name;

    @Size(max = 4096)
    @Column(name = "`desc`", length = 4096)
    private String desc;

    @Size(max = 1024)
    @Column(name = "thumbnail", length = 1024)
    private String thumbnail;

    @Size(max = 1024)
    @Column(name = "source_link", length = 1024)
    private String sourceLink;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = false;

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
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Region.class)
    @JoinColumn(name = "region_id", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private Region region;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Style.class)
    @JoinColumn(name = "style_id", nullable = false, referencedColumnName = "id", insertable = false, updatable = false)
    private Style style;
}