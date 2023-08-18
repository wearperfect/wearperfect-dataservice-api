package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditableDTO {
    Long createdBy;
    Long createdOn;
    Long lastUpdatedBy;
    Long lastUpdatedOn;
    Boolean active;
}
