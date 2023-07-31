package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.PageableResponseDTO;
import com.wearperfect.dataservice.api.dto.ShoppingCartItemDTO;
import com.wearperfect.dataservice.api.dto.ShoppingCartItemDetailsDTO;

public interface ShoppingCartItemService {
    public PageableResponseDTO<ShoppingCartItemDetailsDTO> getShoppingCartItems(Long userId, Integer page, Integer size);

    public ShoppingCartItemDTO getShoppingCartItemById(Long shoppingCartItemId);

    public ShoppingCartItemDTO createShoppingCartItem(ShoppingCartItemDTO shoppingCartItem);

    ShoppingCartItemDTO updateShoppingCartItem(ShoppingCartItemDTO shoppingCartItem);

    public Long deleteShoppingCartItemById(Long shoppingCartItemId);
}
