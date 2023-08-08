package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.constant.Pagination;
import com.wearperfect.dataservice.api.constant.StorePanelCode;
import com.wearperfect.dataservice.api.dto.*;
import com.wearperfect.dataservice.api.entity.Post_;
import com.wearperfect.dataservice.api.entity.StoreCollection;
import com.wearperfect.dataservice.api.entity.StoreCollectionProduct;
import com.wearperfect.dataservice.api.entity.StorePanel;
import com.wearperfect.dataservice.api.mapper.ProductMapper;
import com.wearperfect.dataservice.api.mapper.StoreCollectionMapper;
import com.wearperfect.dataservice.api.mapper.StorePanelMapper;
import com.wearperfect.dataservice.api.repository.StoreCollectionProductRepository;
import com.wearperfect.dataservice.api.repository.StoreCollectionRepository;
import com.wearperfect.dataservice.api.repository.StorePanelRepository;
import com.wearperfect.dataservice.api.service.BrandService;
import com.wearperfect.dataservice.api.service.DesignerService;
import com.wearperfect.dataservice.api.service.StoreCollectionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StoreCollectionServiceImpl implements StoreCollectionService {

    @Autowired
    StoreCollectionRepository storeCollectionRepository;

    @Autowired
    StoreCollectionProductRepository storeCollectionProductRepository;

    @Autowired
    StorePanelRepository storePanelRepository;

    @Autowired
    StoreCollectionMapper storeCollectionMapper;

    @Autowired
    StorePanelMapper storePanelMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    BrandService brandService;

    @Autowired
    DesignerService designerService;

    @Override
    public StoreLatestPanelContentDTO getStoreLatestContent() {
        StoreLatestPanelContentDTO storeLatestPanelContent = new StoreLatestPanelContentDTO();
        Optional<StorePanel> latestPanel = storePanelRepository.findByCode(StorePanelCode.LATEST.getValue());
        if(latestPanel.isPresent()){
            StorePanelBasicDetailsDTO latestPanelDto = storePanelMapper.mapStoreCollectionTypeToStoreCollectionTypeBasicDetailsDTO(latestPanel.get());
            List<StoreCollectionBasicDetailsDTO> featuredCollections = latestPanelDto.getStorePanelCollections().stream().filter(panel -> panel.getFeatured()).map(panel->panel.getStoreCollection()).collect(Collectors.toList());
            List<StoreCollectionBasicDetailsDTO> groupedCollections = latestPanelDto.getStorePanelCollections().stream().filter(panel -> !panel.getFeatured()).map(panel->panel.getStoreCollection()).collect(Collectors.toList());
            List<StoreCollectionBasicDetailsDTO> isolatedCollections = new ArrayList<>();
            storeLatestPanelContent.setFeaturedCollections(featuredCollections);
            storeLatestPanelContent.setGroupedCollections(groupedCollections);
            storeLatestPanelContent.setIsolatedCollections(isolatedCollections);
            storeLatestPanelContent.setNewlyLaunchedBrands(brandService.getNewlyLaunchedBrands());
            storeLatestPanelContent.setNewlyLaunchedDesigners(designerService.getNewlyLaunchedDesigners());
            return storeLatestPanelContent;
        }else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<ProductDetailsDTO> getStoreCollectionProducts(Integer collectionId) {
         List<StoreCollectionProduct> storeCollectionProducts = storeCollectionProductRepository.findByStoreCollectionId(
                 collectionId,
                PageRequest.of(0, Pagination.PageSize.POSTS.getValue(), Sort.by(Sort.Direction.DESC, Post_.CREATED_ON))
        );
        return storeCollectionProducts.stream().map(
                storeCollectionProduct -> productMapper.mapProductToProductDetailsDTO(storeCollectionProduct.getProduct())
        ).collect(Collectors.toList());
    }

    public StoreLatestPanelContentDTO getStoreLatestContent2() {
        List<StoreCollection> collections = storeCollectionRepository.findAll();
        return null;
    }
}
