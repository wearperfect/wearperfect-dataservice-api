package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ProductSpecialSizeChartBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductSpecialSizeChartDTO;
import com.wearperfect.dataservice.api.dto.ProductSpecialSizeChartDetailsDTO;
import com.wearperfect.dataservice.api.entity.ProductSpecialSizeChart;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = { UtilityMapper.class, ProductSpecialSizeMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductSpecialSizeChartMapper {

    ProductSpecialSizeChart mapProductSpecialSizeChartDtoToProductSpecialSizeChart(ProductSpecialSizeChartDTO productSpecialSizeChartDTO);

    ProductSpecialSizeChartDTO mapProductSpecialSizeChartToProductSpecialSizeChartDto(ProductSpecialSizeChart productSpecialSizeChart);

    ProductSpecialSizeChartBasicDetailsDTO mapProductSpecialSizeChartToProductSpecialSizeChartBasicDetailsDto(ProductSpecialSizeChart productSpecialSizeChart);

    ProductSpecialSizeChartDetailsDTO mapProductSpecialSizeChartToProductSpecialSizeChartDetailsDto(ProductSpecialSizeChart productSpecialSizeChart);
}
