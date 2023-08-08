package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.dto.PageableResponseDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionProductBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionProductDTO;
import com.wearperfect.dataservice.api.entity.*;
import com.wearperfect.dataservice.api.mapper.ProductMapper;
import com.wearperfect.dataservice.api.mapper.ProductMediaMapper;
import com.wearperfect.dataservice.api.mapper.WishlistCollectionProductMapper;
import com.wearperfect.dataservice.api.repository.WishlistCollectionProductRepository;
import com.wearperfect.dataservice.api.service.WishlistCollectionProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WishlistCollectionProductServiceImpl implements WishlistCollectionProductService {

    WishlistCollectionProductRepository wishlistCollectionProductRepository;

    WishlistCollectionProductMapper wishlistCollectionProductMapper;

    ProductMediaMapper productMediaMapper;

    ProductMapper productMapper;

    EntityManager em;

    Comparator<com.wearperfect.dataservice.api.entity.ProductMedia> productMediaSequenceComparator =
            Comparator.comparingInt(ProductMedia::getSequenceId);

    public WishlistCollectionProductServiceImpl(WishlistCollectionProductRepository wishlistCollectionProductRepository,
                                                WishlistCollectionProductMapper wishlistCollectionProductMapper,
                                                ProductMediaMapper productMediaMapper,
                                                ProductMapper productMapper,
                                                EntityManager em) {
        this.wishlistCollectionProductRepository = wishlistCollectionProductRepository;
        this.wishlistCollectionProductMapper = wishlistCollectionProductMapper;
        this.productMediaMapper = productMediaMapper;
        this.productMapper = productMapper;
        this.em = em;
    }

    @Override
    public PageableResponseDTO<WishlistCollectionProductBasicDetailsDTO> getWishlistCollectionProducts(Long wishlistCollectionId, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, WishlistCollectionProduct_.CREATED_ON));
        Page<WishlistCollectionProduct> wishlistCollectionProductPage;
        if (wishlistCollectionId != null) {
            wishlistCollectionProductPage = wishlistCollectionProductRepository
                    .findByWishlistCollectionId(wishlistCollectionId, pageRequest);
        } else {
            wishlistCollectionProductPage = wishlistCollectionProductRepository.findAll(pageRequest);
        }
        List<WishlistCollectionProductBasicDetailsDTO> wishlistCollectionProductDetailsDTOList = wishlistCollectionProductPage
                .getContent()
                .stream()
                .map(this::getWishlistCollectionProductDetails).toList();
        PageableResponseDTO<WishlistCollectionProductBasicDetailsDTO> pageableResponseDTO = new PageableResponseDTO<>();
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
    public WishlistCollectionProductBasicDetailsDTO getWishlistCollectionProductById(Long wishlistCollectionProductId) {
        Optional<WishlistCollectionProduct> wishlistCollectionProduct = wishlistCollectionProductRepository.findById(wishlistCollectionProductId);
        if (wishlistCollectionProduct.isPresent()) {
            return getWishlistCollectionProductDetails(wishlistCollectionProduct.get());
        } else {
            throw new EntityNotFoundException("Wishlist Collection Product by ID " + wishlistCollectionProductId + " not found.");
        }
    }

    @Override
    public WishlistCollectionProductDTO createWishlistCollectionProduct(WishlistCollectionProductDTO wishlistCollectionProductDTO) {
        try {
            WishlistCollectionProduct wishlistCollectionProduct = wishlistCollectionProductMapper.mapWishlistCollectionProductDtoToWishlistCollectionProduct(wishlistCollectionProductDTO);
            wishlistCollectionProduct = wishlistCollectionProductRepository.save(wishlistCollectionProduct);
            return wishlistCollectionProductMapper.mapWishlistCollectionProductToWishlistCollectionProductDto(wishlistCollectionProduct);
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
    public Long deleteWishlistCollectionProductById(Long wishlistCollectionProductId) {
        try {
            wishlistCollectionProductRepository.deleteById(wishlistCollectionProductId);
            return wishlistCollectionProductId;
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in removing item in wishlist collection product by ID " + wishlistCollectionProductId + ".");
        }
    }

    public WishlistCollectionProductBasicDetailsDTO getWishlistCollectionProductDetails(WishlistCollectionProduct wishlistCollectionProduct) {
        WishlistCollectionProductBasicDetailsDTO wishlistCollectionProductBasicDetailsDTO = new WishlistCollectionProductBasicDetailsDTO();
        Optional<Product> product = Optional.ofNullable(wishlistCollectionProduct.getProduct());
        wishlistCollectionProductBasicDetailsDTO.setId(wishlistCollectionProduct.getId());
        wishlistCollectionProductBasicDetailsDTO.setProductId(wishlistCollectionProduct.getProductId());
        product.ifPresent(prod -> {
            wishlistCollectionProductBasicDetailsDTO.setProductName(prod.getName());
            wishlistCollectionProductBasicDetailsDTO.setProductDescription(prod.getDescription());
            wishlistCollectionProductBasicDetailsDTO.setProductManufacturerId(prod.getManufacturerId());
            wishlistCollectionProductBasicDetailsDTO.setProductManufacturerName(prod.getManufacturer().getFullname());
            wishlistCollectionProductBasicDetailsDTO.setProductAvailableForSale(prod.getAvailableForSale());
            wishlistCollectionProductBasicDetailsDTO.setProductOutOfStock(checkIfTheProductIsOutOfStock(prod.getProductInventoryItems()));
            wishlistCollectionProductBasicDetailsDTO.setProductPrice(prod.getPrice());
            wishlistCollectionProductBasicDetailsDTO.setProductPriceCurrencyId(prod.getCurrencyId());
            wishlistCollectionProductBasicDetailsDTO.setProductPriceCurrencyName(prod.getCurrency().getName());
            wishlistCollectionProductBasicDetailsDTO.setProductPriceCurrencyShortName(prod.getCurrency().getShortName());
            wishlistCollectionProductBasicDetailsDTO.setProductPriceCurrencyCountryId(prod.getCurrency().getCountryId());
            wishlistCollectionProductBasicDetailsDTO.setProductDiscountValue(prod.getProductDiscount().getValue());
            wishlistCollectionProductBasicDetailsDTO.setProductDiscountTypeName(prod.getProductDiscount().getDiscountType().getName());
            wishlistCollectionProductBasicDetailsDTO.setProductDiscountActive(prod.getProductDiscount().getActive());
            wishlistCollectionProductBasicDetailsDTO.setProductDiscountCouponsAvailable(prod.getProductDiscountCoupons().size() > 0);
            Optional<ProductMedia> productMedia = Optional.ofNullable(getProductThumbnail(prod.getProductMediaList()));
            productMedia.ifPresent(media -> {
                wishlistCollectionProductBasicDetailsDTO.setProductThumbnail(media.getSourceLink());
                wishlistCollectionProductBasicDetailsDTO.setProductThumbnailTitle(media.getTitle());
                wishlistCollectionProductBasicDetailsDTO.setProductThumbnailDescription(media.getDesc());
                wishlistCollectionProductBasicDetailsDTO.setProductThumbnailContentTypeId(media.getContentTypeId());
                wishlistCollectionProductBasicDetailsDTO.setProductThumbnailContentType(media.getContentType().getType());
                wishlistCollectionProductBasicDetailsDTO.setProductThumbnailContentTypeExtension(media.getContentType().getExtension());
            });
        });
        wishlistCollectionProductBasicDetailsDTO.setActive(wishlistCollectionProduct.getActive());
        return wishlistCollectionProductBasicDetailsDTO;
    }

    public Boolean checkIfTheProductIsOutOfStock(List<ProductInventoryItem> productInventoryItemList) {
        return productInventoryItemList.stream().filter(item -> item.getQuantityInStock() > 0).toList().isEmpty();
    }

    public ProductMedia getProductThumbnail(List<ProductMedia> productMediaList) {
        return productMediaList.stream().min(productMediaSequenceComparator).orElse(null);
    }

//    public ProductBasicDetailsDTO getProductBasicDetails(Product product) {
//        ProductBasicDetailsDTO productBasicDetailsDTO = productMapper.mapProductToProductBasicDetailsDTO(product);
//        productBasicDetailsDTO.setOutOfStock(checkIfTheProductIsOutOfStock(product.getProductInventoryItems()));
//        productBasicDetailsDTO.setManufacturerId(product.getManufacturerId());
//        productBasicDetailsDTO.setManufacturerName(product.getManufacturer().getFullname());
//        productBasicDetailsDTO.setThumbnail(
//                productMediaMapper.mapProductMediaToProductMediaBasicDetailsDto(
//                        getProductThumbnail(product.getProductMediaList())
//                )
//        );
//        productBasicDetailsDTO.setProductDiscountCouponsAvailable(
//                product.getProductDiscountCoupons().size() > 0
//        );
//        return productBasicDetailsDTO;
//    }
}
