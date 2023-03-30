package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountTypeBasicDetailsDTO {
    private Byte id;
    private String name;
    private String desc;
    private Boolean active = false;
}
