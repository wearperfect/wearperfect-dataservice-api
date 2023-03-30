package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StorePanelBasicDetailsDTO {
    private Byte id;
    private String name;
    private String desc;
    private String code;
    private Boolean active = false;
    private List<StorePanelCollectionBasicDetailsDTO> storePanelCollections;
}
