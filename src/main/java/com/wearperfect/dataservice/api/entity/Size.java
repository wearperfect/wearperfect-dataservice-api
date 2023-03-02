package com.wearperfect.dataservice.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "sizes")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Short id;

    @javax.validation.constraints.Size(max = 32)
    @NotNull
    @Column(name = "size", nullable = false, length = 32)
    private String size;

    @javax.validation.constraints.Size(max = 32)
    @Column(name = "us_size", length = 32)
    private String usSize;

    @javax.validation.constraints.Size(max = 32)
    @Column(name = "uk_size", length = 32)
    private String ukSize;

    @javax.validation.constraints.Size(max = 32)
    @Column(name = "eu_size", length = 32)
    private String euSize;

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
}