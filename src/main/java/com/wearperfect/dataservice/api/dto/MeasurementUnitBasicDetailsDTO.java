package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasurementUnitBasicDetailsDTO {
    private Byte id;
    private String name;
    private String shortName;
    private Boolean active;
}
