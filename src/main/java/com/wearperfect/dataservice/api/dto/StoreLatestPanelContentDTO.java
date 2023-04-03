package com.wearperfect.dataservice.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoreLatestPanelContentDTO {
    List<StoreCollectionBasicDetailsDTO> featuredCollections;
    List<StoreCollectionBasicDetailsDTO> groupedCollections;
    List<StoreCollectionBasicDetailsDTO> isolatedCollections;
    List<UserBasicDetailsDTO> newlyLaunchedBrands;
    List<UserBasicDetailsDTO> newlyLaunchedDesigners;
}
