package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.StoreLatestContentDTO;
import com.wearperfect.dataservice.api.dto.StorePanelBasicDetailsDTO;

public interface StoreCollectionService {
    StorePanelBasicDetailsDTO getStoreLatestContent();
}
