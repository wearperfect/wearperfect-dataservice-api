package com.wearperfect.dataservice.api.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
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

    @jakarta.validation.constraints.Size(max = 32)
    @NotNull
    @Column(name = "size", nullable = false, length = 32)
    private String size;

    @jakarta.validation.constraints.Size(max = 32)
    @Column(name = "us_size", length = 32)
    private String usSize;

    @jakarta.validation.constraints.Size(max = 32)
    @Column(name = "uk_size", length = 32)
    private String ukSize;

    @jakarta.validation.constraints.Size(max = 32)
    @Column(name = "eu_size", length = 32)
    private String euSize;

    @NotNull
    @Column(name = "sequence_id", nullable = false)
    private Integer sequenceId;

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