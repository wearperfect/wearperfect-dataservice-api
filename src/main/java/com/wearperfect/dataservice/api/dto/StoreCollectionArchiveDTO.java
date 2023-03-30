package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class StoreCollectionArchiveDTO {
    private Integer id;
    private Integer storeCollectionId;
    private String title;
    private String desc;
    private String banner;
    private String thumbnail;
    private String action;
    private Byte sequenceId;
    private Boolean featured = false;
    private Long featuredBy;
    private Boolean active = false;
    private Instant createdOn;
    private Long createdBy;
    private Instant lastUpdatedOn;
    private Long lastUpdatedBy;
}
