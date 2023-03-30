package com.wearperfect.dataservice.api.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wearperfect.dataservice.api.constant.StorePanelCode;
import com.wearperfect.dataservice.api.dto.StorePanelBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.StoreLatestContentDTO;
import com.wearperfect.dataservice.api.entity.StoreCollection;
import com.wearperfect.dataservice.api.entity.StorePanel;
import com.wearperfect.dataservice.api.mapper.StoreCollectionMapper;
import com.wearperfect.dataservice.api.mapper.StorePanelMapper;
import com.wearperfect.dataservice.api.repository.StoreCollectionRepository;
import com.wearperfect.dataservice.api.repository.StorePanelRepository;
import com.wearperfect.dataservice.api.service.StoreCollectionService;
import com.wearperfect.dataservice.api.specification.StorePanelSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StoreCollectionServiceImpl implements StoreCollectionService {

    @Autowired
    StoreCollectionRepository storeCollectionRepository;

    @Autowired
    StorePanelRepository storePanelRepository;

    @Autowired
    StoreCollectionMapper storeCollectionMapper;

    @Autowired
    StorePanelMapper storePanelMapper;

    @Override
    public StorePanelBasicDetailsDTO getStoreLatestContent() {
        Optional<StorePanel> latestPanel = storePanelRepository.findByCode(StorePanelCode.LATEST.getValue());
        if(latestPanel.isPresent()){
            return storePanelMapper.mapStoreCollectionTypeToStoreCollectionTypeBasicDetailsDTO(latestPanel.get());
        }else{
            throw new EntityNotFoundException();
        }
    }

    public StoreLatestContentDTO getStoreLatestContent2() {
        List<StoreCollection> collections = storeCollectionRepository.findAll();
        return null;
    }
}
