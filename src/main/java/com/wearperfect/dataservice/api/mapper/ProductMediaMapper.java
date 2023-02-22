package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ProductMediaDTO;
import com.wearperfect.dataservice.api.entity.ProductMedia;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class, ProductCategoryMapper.class, ContentTypeMapper.class, UserMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMediaMapper {

    ProductMediaDTO mapProductMediaToProductMediaDto(ProductMedia productMedia);

    ProductMedia mapProductMediaDtoToProductMedia(ProductMediaDTO productMediaDTO);
}
