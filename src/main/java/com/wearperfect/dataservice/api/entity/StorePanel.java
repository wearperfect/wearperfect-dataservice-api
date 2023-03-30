package com.wearperfect.dataservice.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "store_panels")
public class StorePanel {
    @Id
    @Column(name = "id", nullable = false)
    private Byte id;

    @Size(max = 32)
    @NotNull
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Size(max = 512)
    @NotNull
    @Column(name = "`desc`", nullable = false, length = 512)
    private String desc;

    @Size(min= 4, max = 4)
    @NotNull
    @Column(name = "code", nullable = false, length = 512)
    private String code;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @NotNull
    @Column(name = "created_on", nullable = false)
    private Instant createdOn;

    @Column(name = "last_updated_by")
    private Long lastUpdatedBy;

    @Column(name = "last_updated_on")
    private Instant lastUpdatedOn;

    @OneToMany(mappedBy = "storePanel", fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = StorePanelCollection.class)
    List<StorePanelCollection> storePanelCollections;
}