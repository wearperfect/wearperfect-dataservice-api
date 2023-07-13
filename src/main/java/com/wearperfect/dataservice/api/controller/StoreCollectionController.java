package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.ProductDetailsDTO;
import com.wearperfect.dataservice.api.dto.StoreLatestPanelContentDTO;
import com.wearperfect.dataservice.api.service.StoreCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreCollectionController {

    @Autowired
    StoreCollectionService storeCollectionService;

    @GetMapping("/v1/store/latest")
    StoreLatestPanelContentDTO getStoreLatestContent(){
        return storeCollectionService.getStoreLatestContent();
    }

    @GetMapping("/v1/store/collections/{collectionId}/products")
    List<ProductDetailsDTO> getStoreCollectionProducts(@PathVariable(name = "collectionId") Integer collectionId){
        return storeCollectionService.getStoreCollectionProducts(collectionId);
    }
}
