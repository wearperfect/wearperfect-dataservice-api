package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.PageableResponseDTO;
import com.wearperfect.dataservice.api.dto.ShoppingCartItemDTO;
import com.wearperfect.dataservice.api.dto.ShoppingCartItemDetailsDTO;
import com.wearperfect.dataservice.api.service.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShoppingCartItemController {
    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    @GetMapping(path = "/v1/shoppingCartItems")
    PageableResponseDTO<ShoppingCartItemDetailsDTO> getShoppingCartItems(
            @RequestParam(name = "userId", required = false) Long userId,
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "20", required = false) Integer size
    ) {
        return shoppingCartItemService.getShoppingCartItems(userId, page, size);
    }

    @GetMapping(path = "/v1/shoppingCartItems/{shoppingCartItemId}")
    ShoppingCartItemDTO getShoppingCartItemById(@PathVariable(name = "shoppingCartItemId", required = true) Long shoppingCartItemId) {
        return shoppingCartItemService.getShoppingCartItemById(shoppingCartItemId);
    }

    @PostMapping(path = "/v1/shoppingCartItems")
    ShoppingCartItemDTO addItemToShoppingCart(@RequestBody ShoppingCartItemDTO shoppingCartItem) {
        return shoppingCartItemService.createShoppingCartItem(shoppingCartItem);
    }

    @PutMapping(path = "/v1/shoppingCartItems")
    ShoppingCartItemDTO updateItemInShoppingCart(@RequestBody ShoppingCartItemDTO shoppingCartItem) {
        return shoppingCartItemService.updateShoppingCartItem(shoppingCartItem);
    }

    @DeleteMapping(path = "/v1/shoppingCartItems/{shoppingCartItemId}")
    Long removeItemFromShoppingCartById(@PathVariable(name = "shoppingCartItemId", required = true) Long shoppingCartItemId) {
        return shoppingCartItemService.deleteShoppingCartItemById(shoppingCartItemId);
    }
}
