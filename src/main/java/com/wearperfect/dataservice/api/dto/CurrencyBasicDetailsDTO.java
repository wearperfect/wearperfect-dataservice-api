package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyBasicDetailsDTO {
    private Byte id;
    private String name;
    private String shortName;
    private String desc;
    private String thumbnail;
    private String sourceLink;
    private Integer countryId;
    private Boolean active;
}
