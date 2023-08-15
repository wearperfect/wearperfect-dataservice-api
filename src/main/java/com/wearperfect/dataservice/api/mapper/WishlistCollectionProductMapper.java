package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.WishlistCollectionProductDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionProductDetailsDTO;
import com.wearperfect.dataservice.api.entity.WishlistCollectionProduct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = {UtilityMapper.class, WishlistProductMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WishlistCollectionProductMapper {
    WishlistCollectionProduct mapWishlistCollectionProductDtoToWishlistCollectionProduct(WishlistCollectionProductDTO wishlistCollectionProductDTO);

    WishlistCollectionProductDTO mapWishlistCollectionProductToWishlistCollectionProductDto(WishlistCollectionProduct wishlistCollectionProduct);

    WishlistCollectionProductDetailsDTO mapWishlistCollectionProductToWishlistCollectionProductDetailsDto(WishlistCollectionProduct wishlistCollectionProduct);
}
