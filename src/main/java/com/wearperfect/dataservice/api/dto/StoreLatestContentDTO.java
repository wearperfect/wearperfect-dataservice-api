package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoreLatestContentDTO {
    StorePanelBasicDetailsDTO featured;
    StorePanelBasicDetailsDTO whatsHot;
    StorePanelBasicDetailsDTO seasonal;
    StorePanelBasicDetailsDTO festive;
    StorePanelBasicDetailsDTO latest;
    List<UserBasicDetailsDTO> newlyLaunchedBrands;
    List<UserBasicDetailsDTO> newlyLaunchedDesigners;
}
