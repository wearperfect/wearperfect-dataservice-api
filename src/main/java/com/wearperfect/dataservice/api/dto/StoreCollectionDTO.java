package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class StoreCollectionDTO {
    private Integer id;
    private String title;
    private String desc;
    private String cover;
    private String thumbnail;
    private String actionText;
    private Boolean active = false;
    private Instant createdOn;
    private Long createdBy;
    private Instant lastUpdatedOn;
    private Long lastUpdatedBy;
}
