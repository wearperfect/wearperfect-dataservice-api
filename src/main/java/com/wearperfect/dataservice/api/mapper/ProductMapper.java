package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ProductBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductDTO;
import com.wearperfect.dataservice.api.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Mapper(uses= {UtilityMapper.class, ProductCategoryMapper.class, GenderCategoryMapper.class, ColorMapper.class,
        UserMapper.class, ProductStyleMapper.class, ProductMediaMapper.class, CurrencyMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    Comparator<ProductMedia> productMediaSequenceComparator = Comparator.comparingInt(ProductMedia::getSequenceId);

    ProductDTO mapProductToProductDto(Product product);

    Product mapProductDtoToProduct(ProductDTO productDTO);

    ProductDetailsDTO mapProductToProductDetailsDTO(Product product);

    default ProductBasicDetailsDTO mapProductToProductBasicDetailsDTO(Product product){
        ProductBasicDetailsDTO productBasicDetailsDTO = new ProductBasicDetailsDTO();
        productBasicDetailsDTO.setId(product.getId());
        productBasicDetailsDTO.setName(product.getName());
        productBasicDetailsDTO.setDescription(product.getDescription());
        productBasicDetailsDTO.setManufacturerId(product.getManufacturerId());
        productBasicDetailsDTO.setManufacturerName(product.getManufacturer().getFullname());
        productBasicDetailsDTO.setAvailableForSale(product.getAvailableForSale());
        productBasicDetailsDTO.setOutOfStock(checkIfTheProductIsOutOfStock(product.getProductInventoryItems()));
        productBasicDetailsDTO.setPrice(product.getPrice());
        productBasicDetailsDTO.setPriceCurrencyId(product.getCurrencyId());
        productBasicDetailsDTO.setCreatedBy(product.getCreatedBy());
        productBasicDetailsDTO.setCreatedOn(product.getCreatedOn());
        productBasicDetailsDTO.setLastUpdatedBy(product.getLastUpdatedBy());
        productBasicDetailsDTO.setLastUpdatedOn(product.getLastUpdatedOn());
        productBasicDetailsDTO.setActive(product.getActive());
        Optional<Currency> optionalCurrency= Optional.ofNullable(product.getCurrency());
        optionalCurrency.ifPresent(currency -> {
            productBasicDetailsDTO.setPriceCurrencyName(currency.getName());
            productBasicDetailsDTO.setPriceCurrencyShortName(currency.getShortName());
            productBasicDetailsDTO.setPriceCurrencyCountryId(currency.getCountryId());
        });
        Optional<ProductDiscount> optionalProductDiscount= Optional.ofNullable(product.getProductDiscount());
        optionalProductDiscount.ifPresent(productDiscount -> {
            productBasicDetailsDTO.setProductDiscountValue(productDiscount.getValue());
            productBasicDetailsDTO.setProductDiscountTypeName(productDiscount.getDiscountType().getName());
            productBasicDetailsDTO.setProductDiscountActive(productDiscount.getActive());
        });
        productBasicDetailsDTO.setProductDiscountCouponsAvailable(product.getProductDiscountCoupons().size() > 0);
        Optional<ProductMedia> productMedia = Optional.ofNullable(getProductThumbnail(product.getProductMediaList()));
        productMedia.ifPresent(media -> {
            productBasicDetailsDTO.setThumbnail(media.getSourceLink());
            productBasicDetailsDTO.setThumbnailTitle(media.getTitle());
            productBasicDetailsDTO.setThumbnailDescription(media.getDesc());
            productBasicDetailsDTO.setThumbnailContentTypeId(media.getContentTypeId());
            productBasicDetailsDTO.setThumbnailContentType(media.getContentType().getType());
            productBasicDetailsDTO.setThumbnailContentTypeExtension(media.getContentType().getExtension());
        });
        return productBasicDetailsDTO;
    }

    default Boolean checkIfTheProductIsOutOfStock(List<ProductInventoryItem> productInventoryItemList) {
        return productInventoryItemList.stream().filter(item -> item.getQuantityInStock() > 0).toList().isEmpty();
    }

    default ProductMedia getProductThumbnail(List<ProductMedia> productMediaList) {
        return productMediaList.stream().min(productMediaSequenceComparator).orElse(null);
    }
}
