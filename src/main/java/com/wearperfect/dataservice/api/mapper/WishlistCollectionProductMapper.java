package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.WishlistCollectionProductDTO;
import com.wearperfect.dataservice.api.dto.WishlistCollectionProductDetailsDTO;
import com.wearperfect.dataservice.api.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Mapper(uses = {UtilityMapper.class, ProductMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WishlistCollectionProductMapper {

    Comparator<ProductMedia> productMediaSequenceComparator = Comparator.comparingInt(ProductMedia::getSequenceId);

    WishlistCollectionProduct mapWishlistCollectionProductDtoToWishlistCollectionProduct(WishlistCollectionProductDTO wishlistCollectionProductDTO);

    WishlistCollectionProductDTO mapWishlistCollectionProductToWishlistCollectionProductDto(WishlistCollectionProduct wishlistCollectionProduct);

    default WishlistCollectionProductDetailsDTO mapWishlistCollectionProductToWishlistCollectionProductDetailsDto(WishlistCollectionProduct wishlistCollectionProduct) {
        WishlistCollectionProductDetailsDTO wishlistCollectionProductDetailsDTO = new WishlistCollectionProductDetailsDTO();
        Optional<Product> product = Optional.ofNullable(wishlistCollectionProduct.getProduct());
        wishlistCollectionProductDetailsDTO.setId(wishlistCollectionProduct.getId());
        wishlistCollectionProductDetailsDTO.setProductId(wishlistCollectionProduct.getProductId());
        product.ifPresent(prod -> {
            wishlistCollectionProductDetailsDTO.setProductName(prod.getName());
            wishlistCollectionProductDetailsDTO.setProductDescription(prod.getDescription());
            wishlistCollectionProductDetailsDTO.setProductManufacturerId(prod.getManufacturerId());
            wishlistCollectionProductDetailsDTO.setProductManufacturerName(prod.getManufacturer().getFullname());
            wishlistCollectionProductDetailsDTO.setProductAvailableForSale(prod.getAvailableForSale());
            wishlistCollectionProductDetailsDTO.setProductOutOfStock(checkIfTheProductIsOutOfStock(prod.getProductInventoryItems()));
            wishlistCollectionProductDetailsDTO.setProductPrice(prod.getPrice());
            wishlistCollectionProductDetailsDTO.setProductPriceCurrencyId(prod.getCurrencyId());
            wishlistCollectionProductDetailsDTO.setProductPriceCurrencyName(prod.getCurrency().getName());
            wishlistCollectionProductDetailsDTO.setProductPriceCurrencyShortName(prod.getCurrency().getShortName());
            wishlistCollectionProductDetailsDTO.setProductPriceCurrencyCountryId(prod.getCurrency().getCountryId());
            Optional<ProductDiscount> productDiscount= Optional.ofNullable(prod.getProductDiscount());
            productDiscount.ifPresent(discount -> {
                wishlistCollectionProductDetailsDTO.setProductDiscountValue(discount.getValue());
                wishlistCollectionProductDetailsDTO.setProductDiscountTypeName(discount.getDiscountType().getName());
                wishlistCollectionProductDetailsDTO.setProductDiscountActive(discount.getActive());
            });
            wishlistCollectionProductDetailsDTO.setProductDiscountCouponsAvailable(prod.getProductDiscountCoupons().size() > 0);
            Optional<ProductMedia> productMedia = Optional.ofNullable(getProductThumbnail(prod.getProductMediaList()));
            productMedia.ifPresent(media -> {
                wishlistCollectionProductDetailsDTO.setProductThumbnail(media.getSourceLink());
                wishlistCollectionProductDetailsDTO.setProductThumbnailTitle(media.getTitle());
                wishlistCollectionProductDetailsDTO.setProductThumbnailDescription(media.getDesc());
                wishlistCollectionProductDetailsDTO.setProductThumbnailContentTypeId(media.getContentTypeId());
                wishlistCollectionProductDetailsDTO.setProductThumbnailContentType(media.getContentType().getType());
                wishlistCollectionProductDetailsDTO.setProductThumbnailContentTypeExtension(media.getContentType().getExtension());
            });
        });
        wishlistCollectionProductDetailsDTO.setActive(wishlistCollectionProduct.getActive());
        return wishlistCollectionProductDetailsDTO;
    }

    default Boolean checkIfTheProductIsOutOfStock(List<ProductInventoryItem> productInventoryItemList) {
        return productInventoryItemList.stream().filter(item -> item.getQuantityInStock() > 0).toList().isEmpty();
    }

    default ProductMedia getProductThumbnail(List<ProductMedia> productMediaList) {
        return productMediaList.stream().min(productMediaSequenceComparator).orElse(null);
    }
}
