package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SizeBasicDetailsDTO {
    private Integer id;
    private String size;
    private String usSize;
    private String ukSize;
    private String euSize;
    private Integer sequenceId;
    private Boolean active;
}
