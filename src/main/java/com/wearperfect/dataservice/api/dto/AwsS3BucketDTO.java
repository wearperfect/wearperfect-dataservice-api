package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class AwsS3BucketDTO {
    Integer id;
    String name;
    String description;
    String region;
    Long createdOn;
    Long createdBy;
    Long lastUpdatedOn;
    Long lastUpdatedBy;
    Boolean active;
}
