package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.StoreCollectionArchiveDTO;
import com.wearperfect.dataservice.api.entity.StoreCollectionArchive;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoreCollectionArchiveMapper {

    StoreCollectionArchive mapStoreCollectionArchiveDtoToStoreCollectionArchive(StoreCollectionArchiveDTO storeCollectionArchiveDto);

    StoreCollectionArchiveDTO mapStoreCollectionArchiveToStoreCollectionArchiveDto(StoreCollectionArchive storeCollectionArchive);
}
