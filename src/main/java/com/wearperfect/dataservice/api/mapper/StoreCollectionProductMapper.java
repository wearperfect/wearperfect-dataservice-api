package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.StoreCollectionProductBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.StoreCollectionProductDTO;
import com.wearperfect.dataservice.api.entity.StoreCollectionProduct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoreCollectionProductMapper {
    StoreCollectionProduct mapStoreCollectionProductDtoToStoreCollectionProduct(StoreCollectionProductDTO storeCollectionProductDTO);

    StoreCollectionProductDTO mapStoreCollectionProductToStoreCollectionProductDto(StoreCollectionProduct storeCollectionProduct);

    StoreCollectionProductBasicDetailsDTO mapStoreCollectionProductToStoreCollectionProductBasicDetailsDto(StoreCollectionProduct storeCollectionProduct);
}
