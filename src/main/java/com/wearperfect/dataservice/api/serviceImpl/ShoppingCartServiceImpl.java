package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.dto.PageableResponseDTO;
import com.wearperfect.dataservice.api.dto.ShoppingCartItemDTO;
import com.wearperfect.dataservice.api.entity.ShoppingCartItem;
import com.wearperfect.dataservice.api.entity.ShoppingCartItem_;
import com.wearperfect.dataservice.api.mapper.ShoppingCartItemMapper;
import com.wearperfect.dataservice.api.repository.ShoppingCartItemRepository;
import com.wearperfect.dataservice.api.service.ShoppingCartService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, ShoppingCartItem_.CREATED_ON));
        Page<ShoppingCartItem> shoppingCartItemList;
        if(userId != null){
            shoppingCartItemList = shoppingCartItemRepository
                    .findByUserId(userId, pageRequest);
        } else {
            shoppingCartItemList = shoppingCartItemRepository.findAll(pageRequest);
        }
        List<ShoppingCartItemDTO> shoppingCartItemDTOList = shoppingCartItemList.getContent().stream()
                .map(item -> shoppingCartItemMapper.mapShoppingCartItemToShoppingCartItemDto(item))
                .toList();
        PageableResponseDTO<ShoppingCartItemDTO> pageableResponseDTO= new PageableResponseDTO<>();
        pageableResponseDTO.setList(shoppingCartItemDTOList);
        pageableResponseDTO.setPage(new PageableResponseDTO.PageMetadata(
                shoppingCartItemList.getSize(),
                shoppingCartItemList.getNumber(),
                shoppingCartItemList.getTotalElements(),
                shoppingCartItemList.getTotalPages()
        ));
        return pageableResponseDTO.getList();
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
