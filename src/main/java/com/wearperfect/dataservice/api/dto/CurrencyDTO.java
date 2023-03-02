package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class CurrencyDTO {
    private Byte id;
    private String name;
    private String shortName;
    private String desc;
    private String thumbnail;
    private String sourceLink;
    private Integer countryId;
    private Boolean active;
    private Instant createdOn;
    private Instant lastUpdatedOn;
    private Long createdBy;
    private Long lastUpdatedBy;
}
