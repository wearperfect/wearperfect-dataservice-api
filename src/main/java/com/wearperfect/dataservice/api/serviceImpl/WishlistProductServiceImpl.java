package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.constant.Pagination;
import com.wearperfect.dataservice.api.dto.PageableResponseDTO;
import com.wearperfect.dataservice.api.dto.WishlistProductDTO;
import com.wearperfect.dataservice.api.dto.WishlistProductDetailsDTO;
import com.wearperfect.dataservice.api.entity.WishlistCollectionProduct_;
import com.wearperfect.dataservice.api.entity.WishlistProduct;
import com.wearperfect.dataservice.api.mapper.ProductMapper;
import com.wearperfect.dataservice.api.mapper.ProductMediaMapper;
import com.wearperfect.dataservice.api.mapper.WishlistProductMapper;
import com.wearperfect.dataservice.api.repository.WishlistProductRepository;
import com.wearperfect.dataservice.api.service.WishlistCollectionProductService;
import com.wearperfect.dataservice.api.service.WishlistCollectionService;
import com.wearperfect.dataservice.api.service.WishlistProductService;
import com.wearperfect.dataservice.api.specification.WishlistProductSpecification;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
public class WishlistProductServiceImpl implements WishlistProductService {
    WishlistProductRepository wishlistProductRepository;

    WishlistProductMapper wishlistProductMapper;

    ProductMediaMapper productMediaMapper;

    ProductMapper productMapper;

    WishlistCollectionService wishlistCollectionService;

    WishlistCollectionProductService wishlistCollectionProductService;

    public WishlistProductServiceImpl(WishlistProductRepository wishlistProductRepository,
                                      WishlistProductMapper wishlistProductMapper,
                                      ProductMediaMapper productMediaMapper,
                                      ProductMapper productMapper,
                                      WishlistCollectionService wishlistCollectionService,
                                      WishlistCollectionProductService wishlistCollectionProductService) {
        this.wishlistProductRepository = wishlistProductRepository;
        this.wishlistProductMapper = wishlistProductMapper;
        this.productMediaMapper = productMediaMapper;
        this.productMapper = productMapper;
        this.wishlistCollectionService = wishlistCollectionService;
        this.wishlistCollectionProductService = wishlistCollectionProductService;
    }

    @Override
    public PageableResponseDTO<WishlistProductDetailsDTO> getWishlistProducts(Long userId, Long wishlistCollectionId, Integer page, Integer size) {
        Page<WishlistProduct> wishlistProductPage = wishlistProductRepository.findAll(
                WishlistProductSpecification.wishlistProductsByUserIdAndWishlistCollectionIdAndActive(userId, wishlistCollectionId, Boolean.TRUE),
                PageRequest.of(
                        page != null ? page : Pagination.PageNumber.DEFAULT.getValue(),
                        size != null ? size : Pagination.PageSize.PRODUCTS.getValue(),
                        Sort.by(Sort.Direction.DESC, WishlistCollectionProduct_.CREATED_ON)
                ));
        return getWishlistPageableResponseDTO(wishlistProductPage);
    }

    @Override
    public Optional<WishlistProductDTO> findByUserIdAndProductId(Long userId, Long productId) {
        Optional<WishlistProduct> optionalWishlistProduct = wishlistProductRepository.findByUserIdAndProductId(userId, productId);
        if (optionalWishlistProduct.isPresent()) {
            WishlistProductDTO wishlistProductDTO = wishlistProductMapper.mapWishlistProductToWishlistProductDto(optionalWishlistProduct.get());
            return Optional.of(wishlistProductDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<WishlistProductDTO> getWishlistProductsByUserIdAndProductIdList(Long userId, List<Long> productIdList) {
        List<WishlistProduct> wishlistProductList = wishlistProductRepository.findByUserIdAndProductIdIn(userId, productIdList);
        return wishlistProductList.stream().map(wishlistProductMapper::mapWishlistProductToWishlistProductDto).toList();
    }

    @Override
    public WishlistProductDetailsDTO getWishlistProductById(Long wishlistProductId) {
        Optional<WishlistProduct> optionalWishlistProduct = wishlistProductRepository.findById(wishlistProductId);
        if (optionalWishlistProduct.isPresent()) {
            return wishlistProductMapper.mapWishlistProductToWishlistProductDetailsDto(optionalWishlistProduct.get());
        } else {
            throw new EntityNotFoundException("Wishlist Collection Product by ID " + optionalWishlistProduct + " not found.");
        }
    }

    @Override
    public WishlistProductDTO createWishlistProduct(WishlistProductDTO wishlistProductDTO) {
        try {
            Optional<WishlistProduct> optionalWishlistProduct = wishlistProductRepository.findByUserIdAndProductId(wishlistProductDTO.getUserId(), wishlistProductDTO.getProductId());
            WishlistProduct wishlistProduct = optionalWishlistProduct.orElseGet(() -> wishlistProductMapper.mapWishlistProductDtoToWishlistProduct(wishlistProductDTO));
            wishlistProduct.setActive(true);
            wishlistProduct = wishlistProductRepository.saveAndFlush(wishlistProduct);
            return wishlistProductMapper.mapWishlistProductToWishlistProductDto(wishlistProduct);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in adding wishlist product by ID " + wishlistProductDTO.getId() + "." + e.getMessage());
        }
    }

    @Override
    public WishlistProductDTO updateWishlistProduct(WishlistProductDTO wishlistProductDTO) {
        try {
            WishlistProduct wishlistProduct = wishlistProductMapper.mapWishlistProductDtoToWishlistProduct(wishlistProductDTO);
            wishlistProduct = wishlistProductRepository.saveAndFlush(wishlistProduct);
            return wishlistProductMapper.mapWishlistProductToWishlistProductDto(wishlistProduct);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in Updating wishlist product by ID " + wishlistProductDTO.getId() + "." + e.getMessage());
        }
    }

    @Override
    public Long deleteWishlistProductById(Long wishlistProductId) {
        //SOFT DELETE WishlistProduct
        //UPDATE WISHLIST COLLECTIONS with wishlistProduct as cover
        //HARD DELETE WishlistCollectionProduct
        try {
            Optional<WishlistProduct> optionalWishlistProduct = wishlistProductRepository.findById(wishlistProductId);
            if (optionalWishlistProduct.isPresent()) {
                WishlistProduct wishlistProduct = optionalWishlistProduct.get();
                wishlistProduct.setActive(false);
                wishlistProduct = wishlistProductRepository.save(wishlistProduct);
                wishlistCollectionService.removeCoverWishlistCollectionProductByWishlistProductIdFromAllWishlistCollections(wishlistProductId);
                wishlistCollectionProductService.deleteByWishlistProductId(wishlistProductId);
                return wishlistProduct.getId();
            } else {
                throw new EntityNotFoundException("Wishlist Collection Product by ID " + optionalWishlistProduct + " not found.");
            }
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error in removing item in wishlist product by ID " + wishlistProductId + ". " + e.getMessage());
        }
    }

    private PageableResponseDTO<WishlistProductDetailsDTO> getWishlistPageableResponseDTO(Page<WishlistProduct> wishlistProductPage) {
        List<WishlistProductDetailsDTO> wishlistProductDetailsDTOList = wishlistProductPage
                .getContent()
                .stream()
                .map(item -> wishlistProductMapper.mapWishlistProductToWishlistProductDetailsDto(item))
                .toList();
        PageableResponseDTO<WishlistProductDetailsDTO> pageableResponseDTO = new PageableResponseDTO<>();
        pageableResponseDTO.setList(wishlistProductDetailsDTOList);
        pageableResponseDTO.setPage(new PageableResponseDTO.PageMetadata(
                wishlistProductPage.getSize(),
                wishlistProductPage.getNumber(),
                wishlistProductPage.getTotalElements(),
                wishlistProductPage.getTotalPages()
        ));
        return pageableResponseDTO;
    }

    //TODO: IMPORTANT Make sure GenericMapper uses all Defined Mappers in this project
//    private <D, E> PageableResponseDTO<D> getPageableResponseDTO(Page<E> page, GenericMapper<E, D> genericMapper) {
//        List<D> list = page
//                .getContent()
//                .stream()
//                .map(genericMapper::mapEntityToDTO)
//                .toList();
//        PageableResponseDTO<D> pageableResponseDTO = new PageableResponseDTO<>();
//        pageableResponseDTO.setList(list);
//        pageableResponseDTO.setPage(new PageableResponseDTO.PageMetadata(
//                page.getSize(),
//                page.getNumber(),
//                page.getTotalElements(),
//                page.getTotalPages()
//        ));
//        return pageableResponseDTO;
//    }
}
