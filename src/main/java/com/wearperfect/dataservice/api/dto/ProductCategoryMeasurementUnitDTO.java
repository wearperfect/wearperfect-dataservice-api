package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductCategoryMeasurementUnitDTO {
    Integer id;
    Integer productCategoryId;
    Integer measurementUnitId;
    Long createdBy;
    Date createdOn;
    Long lastUpdatedBy;
    Date lastUpdatedOn;
    Boolean active;
}
