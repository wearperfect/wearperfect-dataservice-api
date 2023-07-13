package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ProductDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductDTO;
import com.wearperfect.dataservice.api.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class, ProductCategoryMapper.class, GenderCategoryMapper.class, ColorMapper.class,
        UserMapper.class, ProductStyleMapper.class, ProductMediaMapper.class, CurrencyMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductDTO mapProductToProductDto(Product product);

    Product mapProductDtoToProduct(ProductDTO productDTO);

    ProductDetailsDTO mapProductToProductBasicDetailsDTO(Product product);
}
