package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MeasurementUnitDTO {
    private Byte id;
    private String name;
    private String shortName;
    private Boolean active;
    private Long createdBy;
    private Long lastUpdatedBy;
    private Instant createdOn;
    private Instant lastUpdatedOn;
}
