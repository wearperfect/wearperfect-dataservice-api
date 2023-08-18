package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.PageableResponseDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionDetailsDTO;
import com.wearperfect.dataservice.api.security.models.WearperfectUserPrincipal;
import com.wearperfect.dataservice.api.service.WishlistCollectionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class WishlistCollectionController {

    WishlistCollectionService wishlistCollectionService;

    public WishlistCollectionController(WishlistCollectionService wishlistCollectionService) {
        this.wishlistCollectionService = wishlistCollectionService;
    }

    @GetMapping(path = "/v1/wishlistCollections")
    PageableResponseDTO<WishlistCollectionDetailsDTO> getWishlistCollections(
            @RequestParam(name = "userId", required = false) Long userId,
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "20", required = false) Integer size
    ) {
        return wishlistCollectionService.getWishlistCollections(userId, page, size);
    }

    @GetMapping(path = "/v1/wishlistCollections/{wishlistCollectionId}")
    WishlistCollectionDetailsDTO getWishlistCollectionById(@PathVariable(name = "wishlistCollectionId", required = true) Long wishlistCollectionId) {
        return wishlistCollectionService.getWishlistCollectionById(wishlistCollectionId);
    }

    @PostMapping(path = "/v1/wishlistCollections")
    WishlistCollectionDTO addItemToShoppingCart(
            @AuthenticationPrincipal WearperfectUserPrincipal authenticationPrincipal,
            @RequestBody WishlistCollectionDTO wishlistCollectionDTO) {
        System.out.println("authenticationPrincipal:::"+authenticationPrincipal.getUserId());
        return wishlistCollectionService.createWishlistCollection(wishlistCollectionDTO);
    }

    @PutMapping(path = "/v1/wishlistCollections")
    WishlistCollectionDTO updateItemInShoppingCart(@RequestBody WishlistCollectionDTO wishlistCollectionDTO) {
        return wishlistCollectionService.updateWishlistCollection(wishlistCollectionDTO);
    }

    @DeleteMapping(path = "/v1/wishlistCollections/{wishlistCollectionId}")
    Long removeWishlistCollectionById(@PathVariable(name = "wishlistCollectionId", required = true) Long wishlistCollectionId) {
        return wishlistCollectionService.deleteWishlistCollectionById(wishlistCollectionId);
    }
}
