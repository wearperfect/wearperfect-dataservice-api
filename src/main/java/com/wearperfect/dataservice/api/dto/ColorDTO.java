package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class ColorDTO {
    Integer id;
    String name;
    String code;
    String desc;
    Long createdBy;
    Long createdOn;
    Long lastUpdatedBy;
    Long lastUpdatedOn;
    Boolean active;
}
