package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.*;

public interface WishlistProductService {
    PageableResponseDTO<WishlistProductDetailsDTO> getWishlistProducts(Long userId, Long wishlistCollectionId, Integer page, Integer size);

    WishlistProductDetailsDTO getWishlistProductById(Long wishlistCollectionProductId);

    WishlistProductDTO createWishlistProduct(WishlistProductDTO wishlistProductDTO);

    WishlistProductDTO updateWishlistProduct(WishlistProductDTO wishlistProductDTO);

    Long deleteWishlistProductById(Long wishlistProductId);
}
