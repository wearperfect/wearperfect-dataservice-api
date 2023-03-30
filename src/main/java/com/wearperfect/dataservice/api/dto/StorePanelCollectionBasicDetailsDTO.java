package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorePanelCollectionBasicDetailsDTO {
    private Byte id;
    private Byte storePanelId;
    private Byte sequenceId;
    private Boolean featured = false;
    private Boolean active = false;
    private StoreCollectionBasicDetailsDTO storeCollection;
}
