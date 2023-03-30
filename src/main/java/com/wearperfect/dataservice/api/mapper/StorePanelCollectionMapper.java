package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.StorePanelCollectionBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.StorePanelCollectionDTO;
import com.wearperfect.dataservice.api.entity.StorePanelCollection;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class, StoreCollectionMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StorePanelCollectionMapper {
    StorePanelCollection mapStorePanelCollectionDtoToStorePanelCollection(StorePanelCollectionDTO storePanelCollectionDTO);

    StorePanelCollectionDTO mapStorePanelCollectionToStorePanelCollectionDto(StorePanelCollection storePanelCollection);

    StorePanelCollectionBasicDetailsDTO mapStorePanelCollectionToStorePanelCollectionBasicDetailsDTO(StorePanelCollection storePanelCollection);
}
