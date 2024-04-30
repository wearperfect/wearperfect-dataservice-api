package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.PageableResponseDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionProductDetailsDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionProductDTO;

import java.util.List;

public interface WishlistCollectionProductService {
    PageableResponseDTO<WishlistCollectionProductDetailsDTO> getWishlistCollectionProducts(Long wishlistCollectionId, Integer page, Integer size);

    WishlistCollectionProductDetailsDTO getWishlistCollectionProductById(Long wishlistCollectionProductId);

    WishlistCollectionProductDTO createWishlistCollectionProduct(WishlistCollectionProductDTO wishlistCollectionProductDTO);

    WishlistCollectionProductDTO updateWishlistCollectionProduct(WishlistCollectionProductDTO wishlistCollectionProductDTO);

    Long deleteById(Long wishlistCollectionProductId);

    void deleteByWishlistProductId(Long wishlistProductId);
}
