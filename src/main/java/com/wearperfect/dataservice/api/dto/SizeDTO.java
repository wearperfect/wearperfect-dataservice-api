package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class SizeDTO {
    private Long id;
    private String size;
    private String usSize;
    private String ukSize;
    private String euSize;
    private Integer sequenceId;
    private Boolean active;
    private Instant createdOn;
    private Instant lastUpdatedOn;
    private Long createdBy;
    private Long lastUpdatedBy;
}
