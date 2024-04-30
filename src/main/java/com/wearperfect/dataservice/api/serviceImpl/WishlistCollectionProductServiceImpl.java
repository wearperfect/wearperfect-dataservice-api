package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.dto.PageableResponseDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionProductDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionProductDetailsDTO;
import com.wearperfect.dataservice.api.entity.WishlistCollection;
import com.wearperfect.dataservice.api.entity.WishlistCollectionProduct;
import com.wearperfect.dataservice.api.entity.WishlistCollectionProduct_;
import com.wearperfect.dataservice.api.mapper.ProductMapper;
import com.wearperfect.dataservice.api.mapper.ProductMediaMapper;
import com.wearperfect.dataservice.api.mapper.WishlistCollectionProductMapper;
import com.wearperfect.dataservice.api.repository.WishlistCollectionProductRepository;
import com.wearperfect.dataservice.api.repository.WishlistCollectionRepository;
import com.wearperfect.dataservice.api.service.WishlistCollectionProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WishlistCollectionProductServiceImpl implements WishlistCollectionProductService {

    WishlistCollectionProductRepository wishlistCollectionProductRepository;

    WishlistCollectionRepository wishlistCollectionRepository;

    WishlistCollectionProductMapper wishlistCollectionProductMapper;

    ProductMediaMapper productMediaMapper;

    ProductMapper productMapper;

    public WishlistCollectionProductServiceImpl(WishlistCollectionProductRepository wishlistCollectionProductRepository,
                                                WishlistCollectionRepository wishlistCollectionRepository,
                                                WishlistCollectionProductMapper wishlistCollectionProductMapper,
                                                ProductMediaMapper productMediaMapper,
                                                ProductMapper productMapper) {
        this.wishlistCollectionProductRepository = wishlistCollectionProductRepository;
        this.wishlistCollectionRepository = wishlistCollectionRepository;
        this.wishlistCollectionProductMapper = wishlistCollectionProductMapper;
        this.productMediaMapper = productMediaMapper;
        this.productMapper = productMapper;
    }

    @Override
    public PageableResponseDTO<WishlistCollectionProductDetailsDTO> getWishlistCollectionProducts(Long wishlistCollectionId, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, WishlistCollectionProduct_.CREATED_ON)
                        .and(Sort.by(Sort.Direction.DESC, WishlistCollectionProduct_.LAST_UPDATED_ON))
        );
        Page<WishlistCollectionProduct> wishlistCollectionProductPage;
        if (wishlistCollectionId != null) {
            wishlistCollectionProductPage = wishlistCollectionProductRepository
                    .findByWishlistCollectionId(wishlistCollectionId, pageRequest);
        } else {
            wishlistCollectionProductPage = wishlistCollectionProductRepository.findAll(pageRequest);
        }
        List<WishlistCollectionProductDetailsDTO> wishlistCollectionProductDetailsDTOList = wishlistCollectionProductPage
                .getContent()
                .stream()
                .map(item -> wishlistCollectionProductMapper.mapWishlistCollectionProductToWishlistCollectionProductDetailsDto(item))
                .toList();
        PageableResponseDTO<WishlistCollectionProductDetailsDTO> pageableResponseDTO = new PageableResponseDTO<>();
        pageableResponseDTO.setList(wishlistCollectionProductDetailsDTOList);
        pageableResponseDTO.setPage(new PageableResponseDTO.PageMetadata(
                wishlistCollectionProductPage.getSize(),
                wishlistCollectionProductPage.getNumber(),
                wishlistCollectionProductPage.getTotalElements(),
                wishlistCollectionProductPage.getTotalPages()
        ));
        return pageableResponseDTO;
    }

    @Override
    public WishlistCollectionProductDetailsDTO getWishlistCollectionProductById(Long wishlistCollectionProductId) {
        Optional<WishlistCollectionProduct> optionalWishlistCollectionProduct = wishlistCollectionProductRepository.findById(wishlistCollectionProductId);
        if (optionalWishlistCollectionProduct.isPresent()) {
            return wishlistCollectionProductMapper.mapWishlistCollectionProductToWishlistCollectionProductDetailsDto(optionalWishlistCollectionProduct.get());
        } else {
            throw new EntityNotFoundException("Wishlist Collection Product by ID " + wishlistCollectionProductId + " not found.");
        }
    }

    @Override
    public WishlistCollectionProductDTO createWishlistCollectionProduct(WishlistCollectionProductDTO wishlistCollectionProductDTO) {
        try {
            Optional<WishlistCollection> optionalWishlistCollection = wishlistCollectionRepository.findById(wishlistCollectionProductDTO.getWishlistCollectionId());
            if (optionalWishlistCollection.isPresent()) {
                WishlistCollectionProduct wishlistCollectionProduct = wishlistCollectionProductMapper.mapWishlistCollectionProductDtoToWishlistCollectionProduct(wishlistCollectionProductDTO);
                wishlistCollectionProduct = wishlistCollectionProductRepository.save(wishlistCollectionProduct);
                WishlistCollection wishlistCollection = optionalWishlistCollection.get();
                wishlistCollection.setLastUpdatedOn(Instant.now());
                wishlistCollection.setLastUpdatedBy(wishlistCollectionProduct.getCreatedBy());
                wishlistCollectionRepository.save(wishlistCollection);
                return wishlistCollectionProductMapper.mapWishlistCollectionProductToWishlistCollectionProductDto(wishlistCollectionProduct);
            } else {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Wishlist Collection with ID " + wishlistCollectionProductDTO.getWishlistCollectionId() + " not found.");
            }
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in adding wishlist collection product by ID " + wishlistCollectionProductDTO.getId() + "." + e.getMessage());
        }
    }

    @Override
    public WishlistCollectionProductDTO updateWishlistCollectionProduct(WishlistCollectionProductDTO wishlistCollectionProductDTO) {
        try {
            WishlistCollectionProduct wishlistCollectionProduct = wishlistCollectionProductMapper.mapWishlistCollectionProductDtoToWishlistCollectionProduct(wishlistCollectionProductDTO);
            wishlistCollectionProduct = wishlistCollectionProductRepository.save(wishlistCollectionProduct);
            return wishlistCollectionProductMapper.mapWishlistCollectionProductToWishlistCollectionProductDto(wishlistCollectionProduct);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in Updating wishlist collection product by ID " + wishlistCollectionProductDTO.getId() + "." + e.getMessage());
        }
    }

    @Override
    public Long deleteById(Long wishlistCollectionProductId) {
        try {
            wishlistCollectionProductRepository.deleteById(wishlistCollectionProductId);
            return wishlistCollectionProductId;
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in removing item in wishlist collection product by ID " + wishlistCollectionProductId + ".");
        }
    }

    @Override
    public void deleteByWishlistProductId(Long wishlistProductId) {
        try {
            wishlistCollectionProductRepository.deleteByWishlistProductId(wishlistProductId);
        } catch (Exception e) {
            throw new HttpClientErrorException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Error in deleting WishlistCollectionProduct by WishlistProductId %s.", wishlistProductId)
            );
        }
    }
}
