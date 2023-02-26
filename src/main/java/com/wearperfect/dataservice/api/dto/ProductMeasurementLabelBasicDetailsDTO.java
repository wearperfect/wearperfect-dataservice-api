package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductMeasurementLabelBasicDetailsDTO {
    private Integer id;
    private String name;
    private Short sequence;
    private Boolean active;
}
