package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.PageableResponseDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionDetailsDTO;
import com.wearperfect.dataservice.api.entity.WishlistCollection;

import java.util.List;

public interface WishlistCollectionService {
    PageableResponseDTO<WishlistCollectionDetailsDTO> getWishlistCollections(Long userId, Integer page, Integer size);

    WishlistCollectionDetailsDTO getWishlistCollectionById(Long wishlistCollectionId);

    WishlistCollectionDTO createWishlistCollection(WishlistCollectionDTO wishlistCollectionDTO);

    WishlistCollectionDTO updateWishlistCollection(WishlistCollectionDTO wishlistCollectionDTO);

    Long deleteWishlistCollectionById(Long wishlistCollectionId);

    void removeCoverWishlistCollectionProductByWishlistProductIdFromAllWishlistCollections(Long wishlistProductId);
}
