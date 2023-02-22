package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ProductDTO;
import com.wearperfect.dataservice.api.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class, ProductCategoryMapper.class, GenderCategoryMapper.class, UserMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    @Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
    @Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
    ProductDTO mapProductToProductDto(Product product);
}
