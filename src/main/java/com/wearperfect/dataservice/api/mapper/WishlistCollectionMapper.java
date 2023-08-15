package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.WishlistCollectionDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionDetailsDTO;
import com.wearperfect.dataservice.api.entity.WishlistCollection;
import com.wearperfect.dataservice.api.entity.WishlistProduct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Comparator;

@Mapper(uses = {UtilityMapper.class, WishlistCollectionProductMapper.class, WishlistProductMapper.class, ProductMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WishlistCollectionMapper {
    WishlistCollection mapWishlistCollectionDtoToWishlistCollection(WishlistCollectionDTO wishlistCollectionDTO);

    WishlistCollectionDTO mapWishlistCollectionToWishlistCollectionDto(WishlistCollection wishlistCollection);

    WishlistCollectionDetailsDTO mapWishlistCollectionToWishlistCollectionDetailsDto(WishlistCollection wishlistCollection);
}
