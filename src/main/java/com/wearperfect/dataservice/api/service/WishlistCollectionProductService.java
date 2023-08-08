package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.PageableResponseDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionProductBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionProductDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionProductDetailsDTO;

public interface WishlistCollectionProductService {
    PageableResponseDTO<WishlistCollectionProductBasicDetailsDTO> getWishlistCollectionProducts(Long wishlistCollectionId, Integer page, Integer size);

    WishlistCollectionProductBasicDetailsDTO getWishlistCollectionProductById(Long wishlistCollectionProductId);

    WishlistCollectionProductDTO createWishlistCollectionProduct(WishlistCollectionProductDTO wishlistCollectionProductDTO);

    WishlistCollectionProductDTO updateWishlistCollectionProduct(WishlistCollectionProductDTO wishlistCollectionProductDTO);

    Long deleteWishlistCollectionProductById(Long wishlistCollectionProductId);
}
