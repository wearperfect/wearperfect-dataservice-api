package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.WishlistCollectionProductDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionProductDetailsDTO;
import com.wearperfect.dataservice.api.entity.WishlistCollectionProduct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = {UtilityMapper.class, ProductMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WishlistCollectionProductMapper {

    public WishlistCollectionProduct mapWishlistCollectionProductDtoToWishlistCollectionProduct(WishlistCollectionProductDTO wishlistCollectionProductDTO);

    public WishlistCollectionProductDTO mapWishlistCollectionProductToWishlistCollectionProductDto(WishlistCollectionProduct wishlistCollectionProduct);

    public WishlistCollectionProductDetailsDTO mapWishlistCollectionProductToWishlistCollectionProductDetailsDto(WishlistCollectionProduct wishlistCollectionProduct);
}
