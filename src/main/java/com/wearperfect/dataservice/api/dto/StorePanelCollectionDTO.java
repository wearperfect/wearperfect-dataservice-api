package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class StorePanelCollectionDTO {
    private Byte id;
    private Byte storePanelId;
    private Integer storeCollectionId;
    private Byte sequenceId;
    private Boolean featured = false;
    private Boolean active = false;
    private Long createdBy;
    private Instant createdOn;
    private Long lastUpdatedBy;
    private Instant lastUpdatedOn;
}
