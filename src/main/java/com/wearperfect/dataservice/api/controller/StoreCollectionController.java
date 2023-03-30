package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.StoreLatestContentDTO;
import com.wearperfect.dataservice.api.dto.StorePanelBasicDetailsDTO;
import com.wearperfect.dataservice.api.service.StoreCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreCollectionController {

    @Autowired
    StoreCollectionService storeCollectionService;

    @GetMapping("/v1/store/latest")
    StorePanelBasicDetailsDTO getStoreLatestContent(){
        return storeCollectionService.getStoreLatestContent();
    }
}
