package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.dto.ShoppingCartItemDTO;
import com.wearperfect.dataservice.api.entity.ShoppingCartItem;
import com.wearperfect.dataservice.api.mapper.ShoppingCartItemMapper;
import com.wearperfect.dataservice.api.repository.ShoppingCartItemRepository;
import com.wearperfect.dataservice.api.service.ShoppingCartService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    private ShoppingCartItemMapper shoppingCartItemMapper;

    @Override
    public List<ShoppingCartItemDTO> getAllShoppingCartItemsByUserId(Long userId) {
        List<ShoppingCartItem> shoppingCartItemList = shoppingCartItemRepository.findAllByUserId(userId);
        return shoppingCartItemList.stream()
                .map(item -> shoppingCartItemMapper.mapShoppingCartItemToShoppingCartItemDto(item))
                .collect(Collectors.toList());
    }

    @Override
    public ShoppingCartItemDTO getShoppingCartItemById(Long shoppingCartItemId) {
        Optional<ShoppingCartItem> shoppingCartItem = shoppingCartItemRepository.findById(shoppingCartItemId);
        if(shoppingCartItem.isPresent()){
            return shoppingCartItemMapper.mapShoppingCartItemToShoppingCartItemDto(shoppingCartItem.get());
        }else {
            throw new EntityNotFoundException("Shopping cart item by ID "+shoppingCartItem+" not found.");
        }
    }

    @Override
    public ShoppingCartItemDTO addItemToShoppingCart(ShoppingCartItemDTO shoppingCartItemDTO) {
        try{
            ShoppingCartItem shoppingCartItem = shoppingCartItemMapper.mapShoppingCartItemDtoToShoppingCartItem(shoppingCartItemDTO);
            shoppingCartItem.setCreatedOn(Instant.now());
            ShoppingCartItem addedShoppingCartItem = shoppingCartItemRepository.save(shoppingCartItem);
            return  shoppingCartItemMapper.mapShoppingCartItemToShoppingCartItemDto(addedShoppingCartItem);
        } catch (Exception e){
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in adding item to shopping cart.");
        }
    }

    @Override
    public ShoppingCartItemDTO updateItemInShoppingCart(ShoppingCartItemDTO shoppingCartItemDTO) {
        try{
            ShoppingCartItem shoppingCartItem = shoppingCartItemMapper.mapShoppingCartItemDtoToShoppingCartItem(shoppingCartItemDTO);
            ShoppingCartItem addedShoppingCartItem = shoppingCartItemRepository.save(shoppingCartItem);
            return  shoppingCartItemMapper.mapShoppingCartItemToShoppingCartItemDto(addedShoppingCartItem);
        } catch (Exception e){
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in updating item in shopping cart.");
        }
    }

    @Override
    public Long removeItemFromShoppingCartById(Long shoppingCartItemId) {
        try{
            ShoppingCartItem addedShoppingCartItem = shoppingCartItemRepository.removeById(shoppingCartItemId);
            return  addedShoppingCartItem.getId();
        } catch (Exception e){
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in updating item in shopping cart.");
        }
    }
}
