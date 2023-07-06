package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartDTO;
import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartDetailsDTO;
import com.wearperfect.dataservice.api.entity.ProductCategorySizeChart;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = { UtilityMapper.class, ProductCategorySizeMapper.class, GenderCategoryMapper.class, UserMapper.class, ProductCategoryMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductCategorySizeChartMapper {

    ProductCategorySizeChartDTO mapProductCategorySizeChartToProductCategorySizeChartDto(ProductCategorySizeChart productCategorySizeChart);

    ProductCategorySizeChart mapProductCategorySizeChartDtoToProductCategorySizeChart(ProductCategorySizeChartDTO productCategorySizeChartDTO);

    ProductCategorySizeChartDetailsDTO mapProductCategorySizeChartToProductCategorySizeChartDetailsDto(ProductCategorySizeChart productCategorySizeChart);

    ProductCategorySizeChartBasicDetailsDTO mapProductCategorySizeChartToProductCategorySizeChartBasicDetailsDto(ProductCategorySizeChart productCategorySizeChart);
}
