package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.ProductBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.StoreLatestPanelContentDTO;

import java.util.List;

public interface StoreCollectionService {
    StoreLatestPanelContentDTO getStoreLatestContent();

    List<ProductBasicDetailsDTO> getStoreCollectionProducts(Integer collectionId);
}
