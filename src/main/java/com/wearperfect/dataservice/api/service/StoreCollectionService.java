package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.ProductDetailsDTO;
import com.wearperfect.dataservice.api.dto.StoreLatestPanelContentDTO;

import java.util.List;

public interface StoreCollectionService {
    StoreLatestPanelContentDTO getStoreLatestContent();

    List<ProductDetailsDTO> getStoreCollectionProducts(Integer collectionId);
}
