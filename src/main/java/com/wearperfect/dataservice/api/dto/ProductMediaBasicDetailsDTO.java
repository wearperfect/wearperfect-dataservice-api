package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductMediaBasicDetailsDTO {
    private Long id;
    private Long productId;
    private String title;
    private String desc;
    private Byte sequenceId;
    private String sourceLink;
    private Boolean active;
    private ContentTypeBasicDetailsDTO contentType;
}
