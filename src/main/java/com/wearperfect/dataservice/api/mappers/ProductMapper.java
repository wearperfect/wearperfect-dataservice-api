package com.wearperfect.dataservice.api.mappers;

import com.wearperfect.dataservice.api.dto.ProductDTO;
import com.wearperfect.dataservice.api.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductDTO mapProductToProduct(Product product);
}
