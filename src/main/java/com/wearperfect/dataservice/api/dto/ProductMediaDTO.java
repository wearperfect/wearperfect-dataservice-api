package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ProductMediaDTO {
    private Long id;
    private Long productId;
    private String title;
    private String desc;
    private Byte sequenceId;
    private String sourceLink;
    private Integer contentTypeId;
    private Boolean active = false;
    private Instant createdOn;
    private Instant lastUpdatedOn;
    private Long createdBy;
    private Long lastUpdatedBy;
    private ContentTypeBasicDetailsDTO contentType;
}
