package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.StoreCollectionBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.StoreCollectionDTO;
import com.wearperfect.dataservice.api.entity.StoreCollection;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoreCollectionMapper {
    StoreCollection mapStoreCollectionDtoToStoreCollection(StoreCollectionDTO storeCollectionDTO);

    StoreCollectionDTO mapStoreCollectionToStoreCollectionDto(StoreCollection storeCollection);

    StoreCollectionBasicDetailsDTO mapStoreCollectionToStoreCollectionBasicDetailsDto(StoreCollection storeCollection);
}
