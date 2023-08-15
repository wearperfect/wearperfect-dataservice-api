package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.WishlistProductDTO;
import com.wearperfect.dataservice.api.dto.WishlistProductDetailsDTO;
import com.wearperfect.dataservice.api.entity.WishlistProduct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = {UtilityMapper.class, ProductMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WishlistProductMapper {
    WishlistProduct mapWishlistProductDtoToWishlistProduct(WishlistProductDTO wishlistProductDTO);

    WishlistProductDTO mapWishlistProductToWishlistProductDto(WishlistProduct wishlistProduct);

    WishlistProductDetailsDTO mapWishlistProductToWishlistProductDetailsDto(WishlistProduct wishlistProduct);
}
