package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.PageableResponseDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionProductDetailsDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionProductDTO;
import com.wearperfect.dataservice.api.service.WishlistCollectionProductService;
import org.springframework.web.bind.annotation.*;

@RestController
public class WishlistCollectionProductController {
    WishlistCollectionProductService wishlistCollectionProductService;

    public WishlistCollectionProductController(WishlistCollectionProductService WishlistCollectionProductService) {
        this.wishlistCollectionProductService = WishlistCollectionProductService;
    }

    @GetMapping(path = "/v1/wishlistCollectionProducts")
    PageableResponseDTO<WishlistCollectionProductDetailsDTO> getWishlistCollectionProducts(
            @RequestParam(name = "wishlistCollectionId", required = false) Long wishlistCollectionId,
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "20", required = false) Integer size
    ) {
        return wishlistCollectionProductService.getWishlistCollectionProducts(wishlistCollectionId, page, size);
    }

    @GetMapping(path = "/v1/wishlistCollectionProducts/{wishlistCollectionProductId}")
    WishlistCollectionProductDetailsDTO getWishlistCollectionProductById(@PathVariable(name = "wishlistCollectionProductId", required = true) Long wishlistCollectionProductId) {
        return wishlistCollectionProductService.getWishlistCollectionProductById(wishlistCollectionProductId);
    }

    @PostMapping(path = "/v1/wishlistCollectionProducts")
    WishlistCollectionProductDTO addItemToShoppingCart(@RequestBody WishlistCollectionProductDTO wishlistCollectionProductDTO) {
        return wishlistCollectionProductService.createWishlistCollectionProduct(wishlistCollectionProductDTO);
    }

    @PutMapping(path = "/v1/wishlistCollectionProducts")
    WishlistCollectionProductDTO updateItemInShoppingCart(@RequestBody WishlistCollectionProductDTO wishlistCollectionProductDTO) {
        return wishlistCollectionProductService.updateWishlistCollectionProduct(wishlistCollectionProductDTO);
    }

    @DeleteMapping(path = "/v1/wishlistCollectionProducts/{wishlistCollectionProductId}")
    Long removeWishlistCollectionProductById(@PathVariable(name = "wishlistCollectionProductId", required = true) Long wishlistCollectionProductId) {
        return wishlistCollectionProductService.deleteWishlistCollectionProductById(wishlistCollectionProductId);
    }
}
