package com.wearperfect.dataservice.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "store_panel_collections")
public class StorePanelCollection {
    @Id
    @Column(name = "id", nullable = false)
    private Byte id;

    @NotNull
    @Column(name = "store_panel_id", nullable = false)
    private Byte storePanelId;

    @NotNull
    @Column(name = "store_collection_id", nullable = false)
    private Integer storeCollectionId;

    @NotNull
    @Column(name = "sequence_id", nullable = false)
    private Byte sequenceId;

    @NotNull
    @Column(name = "featured", nullable = false)
    private Boolean featured = false;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = StorePanel.class)
    @JoinColumn(name = "store_panel_id", nullable = false, insertable = false, updatable = false)
    private StorePanel storePanel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = StoreCollection.class)
    @JoinColumn(name = "store_collection_id", nullable = false, insertable = false, updatable = false)
    private StoreCollection storeCollection;
}
