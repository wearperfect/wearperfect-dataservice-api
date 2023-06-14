package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.ShoppingCartItemDTO;

import java.util.List;

public interface ShoppingCartService {

    public List<ShoppingCartItemDTO> getAllShoppingCartItemsByUserId(Long userId);

    public ShoppingCartItemDTO getShoppingCartItemById(Long shoppingCartItemId);

    public ShoppingCartItemDTO addItemToShoppingCart(ShoppingCartItemDTO shoppingCartItem);

    ShoppingCartItemDTO updateItemInShoppingCart(ShoppingCartItemDTO shoppingCartItem);

    public Long removeItemFromShoppingCartById(Long shoppingCartItemId);
}
