package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.StorePanelBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.StorePanelDTO;
import com.wearperfect.dataservice.api.entity.StorePanel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class, StorePanelCollectionMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StorePanelMapper {
    StorePanel mapStoreCollectionDtoTypeToStoreCollectionType(StorePanelDTO storePanelDTO);

    StorePanelDTO mapStoreCollectionTypeToStoreCollectionTypeDto(StorePanel storePanel);

    StorePanelBasicDetailsDTO mapStoreCollectionTypeToStoreCollectionTypeBasicDetailsDTO(StorePanel storePanel);
}
