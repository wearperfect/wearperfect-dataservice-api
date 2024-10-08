package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.dto.*;
import com.wearperfect.dataservice.api.entity.ShoppingCartItem;
import com.wearperfect.dataservice.api.entity.ShoppingCartItem_;
import com.wearperfect.dataservice.api.mapper.ShoppingCartItemMapper;
import com.wearperfect.dataservice.api.repository.ShoppingCartItemRepository;
import com.wearperfect.dataservice.api.service.ProductService;
import com.wearperfect.dataservice.api.service.ShoppingCartItemService;
import com.wearperfect.dataservice.api.service.SizeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {
    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    private ShoppingCartItemMapper shoppingCartItemMapper;

    @Autowired
    ProductService productService;

    @Autowired
    SizeService sizeService;

    @Override
    public PageableResponseDTO<ShoppingCartItemDetailsDTO> getShoppingCartItems(Long userId, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, ShoppingCartItem_.CREATED_ON));
        Page<ShoppingCartItem> shoppingCartItemPage;
        if (userId != null) {
            shoppingCartItemPage = shoppingCartItemRepository
                    .findByUserId(userId, pageRequest);
        } else {
            shoppingCartItemPage = shoppingCartItemRepository.findAll(pageRequest);
        }
        List<ShoppingCartItemDetailsDTO> shoppingCartItemDetailsDTOList = shoppingCartItemPage.getContent().stream()
                .map(item -> shoppingCartItemMapper.mapShoppingCartItemToShoppingCartItemDetailsDto(item))
                .toList();
        PageableResponseDTO<ShoppingCartItemDetailsDTO> pageableResponseDTO = new PageableResponseDTO<>();
        pageableResponseDTO.setList(shoppingCartItemDetailsDTOList);
        pageableResponseDTO.setPage(new PageableResponseDTO.PageMetadata(
                shoppingCartItemPage.getSize(),
                shoppingCartItemPage.getNumber(),
                shoppingCartItemPage.getTotalElements(),
                shoppingCartItemPage.getTotalPages()
        ));
        return pageableResponseDTO;
    }

    @Override
    public ShoppingCartItemDTO getShoppingCartItemById(Long shoppingCartItemId) {
        Optional<ShoppingCartItem> shoppingCartItem = shoppingCartItemRepository.findById(shoppingCartItemId);
        if (shoppingCartItem.isPresent()) {
            return shoppingCartItemMapper.mapShoppingCartItemToShoppingCartItemDto(shoppingCartItem.get());
        } else {
            throw new EntityNotFoundException("Shopping cart item by ID " + shoppingCartItemId + " not found.");
        }
    }

    @Override
    public ShoppingCartItemDTO createShoppingCartItem(ShoppingCartItemDTO shoppingCartItemDTO) {
        try {
            ShoppingCartItem shoppingCartItem = shoppingCartItemMapper.mapShoppingCartItemDtoToShoppingCartItem(shoppingCartItemDTO);
            Optional<ShoppingCartItem> existingShoppingCartItem = shoppingCartItemRepository.findByUserIdAndProductIdAndSizeId(
                    shoppingCartItem.getUserId(),
                    shoppingCartItem.getProductId(),
                    shoppingCartItem.getSizeId()
            );
            if (existingShoppingCartItem.isPresent()) {
                existingShoppingCartItem.get().setQuantity((short) (existingShoppingCartItem.get().getQuantity() + shoppingCartItem.getQuantity()));
                shoppingCartItem = existingShoppingCartItem.get();
            }
            shoppingCartItem = shoppingCartItemRepository.save(shoppingCartItem);
            return shoppingCartItemMapper.mapShoppingCartItemToShoppingCartItemDto(shoppingCartItem);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in adding item to shopping cart." + e.getMessage());
        }
    }

    @Override
    public ShoppingCartItemDTO updateShoppingCartItem(ShoppingCartItemDTO shoppingCartItemDTO) {
        try {
            ShoppingCartItem shoppingCartItem = shoppingCartItemMapper.mapShoppingCartItemDtoToShoppingCartItem(shoppingCartItemDTO);
            if (shoppingCartItem.getQuantity() == 0) {
                deleteShoppingCartItemById(shoppingCartItem.getId());
            } else {
                shoppingCartItem = shoppingCartItemRepository.save(shoppingCartItem);
            }
            return shoppingCartItemMapper.mapShoppingCartItemToShoppingCartItemDto(shoppingCartItem);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in updating item in shopping cart. " + e.getMessage());
        }
    }

    @Override
    public Long deleteShoppingCartItemById(Long shoppingCartItemId) {
        try {
            shoppingCartItemRepository.deleteById(shoppingCartItemId);
            return shoppingCartItemId;
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in removing item in shopping cart item by ID "+shoppingCartItemId+".");
        }
    }
}
