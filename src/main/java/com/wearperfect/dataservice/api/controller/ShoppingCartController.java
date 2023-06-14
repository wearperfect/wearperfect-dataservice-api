package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.ShoppingCartItemDTO;
import com.wearperfect.dataservice.api.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping(path = "/v1/shopping-cart/items/userId/{userId}")
    List<ShoppingCartItemDTO> getAllShoppingCartItemsByUserId(@PathVariable(name = "userId", required = true) Long userId) {
        return shoppingCartService.getAllShoppingCartItemsByUserId(userId);
    }

    @GetMapping(path = "/v1/shopping-cart/items/{shoppingCartItemId}")
    ShoppingCartItemDTO getShoppingCartItemById(@PathVariable(name = "shoppingCartItemId", required = true) Long shoppingCartItemId) {
        return shoppingCartService.getShoppingCartItemById(shoppingCartItemId);
    }

    @PostMapping(path = "/v1/shopping-cart/items")
    ShoppingCartItemDTO addItemToShoppingCart(@RequestBody ShoppingCartItemDTO shoppingCartItem) {
        return shoppingCartService.addItemToShoppingCart(shoppingCartItem);
    }

    @PutMapping(path = "/v1/shopping-cart/items")
    ShoppingCartItemDTO updateItemInShoppingCart(@RequestBody ShoppingCartItemDTO shoppingCartItem) {
        return shoppingCartService.updateItemInShoppingCart(shoppingCartItem);
    }

    @DeleteMapping(path = "/v1/shopping-cart/items/{shoppingCartItemId}")
    Long removeItemFromShoppingCartById(@PathVariable(name = "shoppingCartItemId", required = true) Long shoppingCartItemId) {
        return shoppingCartService.removeItemFromShoppingCartById(shoppingCartItemId);
    }
}
