package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.PageableResponseDTO;
import com.wearperfect.dataservice.api.dto.WishlistProductDTO;
import com.wearperfect.dataservice.api.dto.WishlistProductDetailsDTO;
import com.wearperfect.dataservice.api.service.WishlistProductService;
import org.springframework.web.bind.annotation.*;

@RestController
public class WishlistProductController {

    WishlistProductService wishlistProductService;

    public WishlistProductController(WishlistProductService wishlistProductService) {
        this.wishlistProductService = wishlistProductService;
    }

    @GetMapping(path = "/v1/wishlistProducts")
    PageableResponseDTO<WishlistProductDetailsDTO> getWishlistProducts(
            @RequestParam(name = "userId", required = false) Long userId,
            @RequestParam(name = "wishlistCollectionId", required = false) Long wishlistCollectionId,
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "20", required = false) Integer size
    ) {
        return wishlistProductService.getWishlistProducts(userId, wishlistCollectionId, page, size);
    }

    @GetMapping(path = "/v1/wishlistProducts/{wishlistProductId}")
    WishlistProductDetailsDTO getWishlistProductById(@PathVariable(name = "wishlistProductId") Long wishlistProductId) {
        return wishlistProductService.getWishlistProductById(wishlistProductId);
    }

    @PostMapping(path = "/v1/wishlistProducts")
    WishlistProductDTO addItemToShoppingCart(@RequestBody WishlistProductDTO wishlistProductDTO) {
        return wishlistProductService.createWishlistProduct(wishlistProductDTO);
    }

    @PutMapping(path = "/v1/wishlistProducts")
    WishlistProductDTO updateItemInShoppingCart(@RequestBody WishlistProductDTO wishlistProductDTO) {
        return wishlistProductService.updateWishlistProduct(wishlistProductDTO);
    }

    @DeleteMapping(path = "/v1/wishlistProducts/{wishlistProductId}")
    Long removeWishlistProductById(@PathVariable(name = "wishlistProductId") Long wishlistProductId) {
        return wishlistProductService.deleteWishlistProductById(wishlistProductId);
    }
}
