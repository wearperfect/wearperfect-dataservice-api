package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class RegionStyleDTO {
    private Integer id;
    private Integer styleId;
    private Integer regionId;
    private String name;
    private String desc;
    private String thumbnail;
    private String sourceLink;
    private Boolean active;
    private Instant createdOn;
    private Long createdBy;
    private Instant lastUpdatedOn;
    private Long lastUpdatedBy;
    private ProductStyleDTO productStyle;
    private RegionBasicDetailsDTO region;
}
