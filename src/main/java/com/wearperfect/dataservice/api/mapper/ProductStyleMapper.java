package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ProductStyleDTO;
import com.wearperfect.dataservice.api.entity.ProductStyle;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class, StyleMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductStyleMapper {

    ProductStyleDTO mapProductStyleToProductStyleDto(ProductStyle productStyle);

    ProductStyle mapProductStyleDtoToProductStyle(ProductStyleDTO productStyleDTO);
}
