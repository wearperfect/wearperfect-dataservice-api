package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.PageableResponseDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionProductDetailsDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionProductDTO;

public interface WishlistCollectionProductService {
    PageableResponseDTO<WishlistCollectionProductDetailsDTO> getWishlistCollectionProducts(Long wishlistCollectionId, Integer page, Integer size);

    WishlistCollectionProductDetailsDTO getWishlistCollectionProductById(Long wishlistCollectionProductId);

    WishlistCollectionProductDTO createWishlistCollectionProduct(WishlistCollectionProductDTO wishlistCollectionProductDTO);

    WishlistCollectionProductDTO updateWishlistCollectionProduct(WishlistCollectionProductDTO wishlistCollectionProductDTO);

    Long deleteWishlistCollectionProductById(Long wishlistCollectionProductId);
}
