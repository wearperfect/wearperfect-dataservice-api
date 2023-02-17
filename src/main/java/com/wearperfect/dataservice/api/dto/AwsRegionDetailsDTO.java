package com.wearperfect.dataservice.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class AwsRegionDetailsDTO {
    Integer id;
    String name;
    String description;
    Long createdOn;
    Long createdBy;
    Long lastUpdatedOn;
    Long lastUpdatedBy;
    Boolean active;
    List<AwsS3BucketDTO> s3BucketsList;
}
