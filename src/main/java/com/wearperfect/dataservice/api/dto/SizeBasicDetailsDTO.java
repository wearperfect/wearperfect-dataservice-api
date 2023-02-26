package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SizeBasicDetailsDTO {
    private Long id;
    private String size;
    private String usSize;
    private String ukSize;
    private String desc;
    private Boolean active;
}
