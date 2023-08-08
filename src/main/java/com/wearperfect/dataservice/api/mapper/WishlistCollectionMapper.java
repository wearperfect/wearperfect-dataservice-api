package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.WishlistCollectionDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionDetailsDTO;
import com.wearperfect.dataservice.api.entity.WishlistCollection;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = {UtilityMapper.class, WishlistCollectionProductMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WishlistCollectionMapper {

    WishlistCollection mapWishlistCollectionDtoToWishlistCollection(WishlistCollectionDTO wishlistCollectionDTO);

    WishlistCollectionDTO mapWishlistCollectionToWishlistCollectionDto(WishlistCollection wishlistCollection);

    WishlistCollectionDetailsDTO mapWishlistCollectionToWishlistCollectionDetailsDto(WishlistCollection wishlistCollection);
}
