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
@Table(name = "currencies", schema = "wearperfect")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Byte id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 16)
    @NotNull
    @Column(name = "short_name", nullable = false, length = 16)
    private String shortName;

    @Size(max = 4096)
    @Column(name = "`desc`", length = 4096)
    private String desc;

    @Size(max = 1024)
    @NotNull
    @Column(name = "thumbnail", nullable = false, length = 1024)
    private String thumbnail;

    @Size(max = 1024)
    @Column(name = "source_link", length = 1024)
    private String sourceLink;

    @Column(name = "country_id")
    private Integer countryId;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active;

    @NotNull
    @Column(name = "created_on", nullable = false)
    private Instant createdOn;

    @Column(name = "last_updated_on")
    private Instant lastUpdatedOn;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "last_updated_by")
    private Long lastUpdatedBy;
}