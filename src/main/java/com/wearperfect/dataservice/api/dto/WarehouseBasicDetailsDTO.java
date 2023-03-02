package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseBasicDetailsDTO {
    private Short id;
    private String name;
    private String desc;
    private Long addressId;
    private Boolean active;
}
