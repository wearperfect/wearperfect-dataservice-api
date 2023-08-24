package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.PageableResponseDTO;
import com.wearperfect.dataservice.api.dto.WishlistProductDTO;
import com.wearperfect.dataservice.api.dto.WishlistProductDetailsDTO;

import java.util.List;
import java.util.Optional;

public interface WishlistProductService {
    PageableResponseDTO<WishlistProductDetailsDTO> getWishlistProducts(Long userId, Long wishlistCollectionId, Integer page, Integer size);

    Optional<WishlistProductDTO> findByUserIdAndProductId(Long userId, Long productId);

    List<WishlistProductDTO> getWishlistProductsByUserIdAndProductIdList(Long userId, List<Long> productIdList);

    WishlistProductDetailsDTO getWishlistProductById(Long wishlistCollectionProductId);

    WishlistProductDTO createWishlistProduct(WishlistProductDTO wishlistProductDTO);

    WishlistProductDTO updateWishlistProduct(WishlistProductDTO wishlistProductDTO);

    Long deleteWishlistProductById(Long wishlistProductId);
}
