package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartDTO;

import java.util.List;

public interface ProductCategorySizeChartService {
    List<ProductCategorySizeChartBasicDetailsDTO> getProductCategorySizeCharts();

    ProductCategorySizeChartBasicDetailsDTO getProductCategorySizeChartById(Integer productCategorySizeChartId);

    ProductCategorySizeChartBasicDetailsDTO createProductCategorySizeChart(ProductCategorySizeChartDTO productCategorySizeChartDTO);

    ProductCategorySizeChartBasicDetailsDTO updateProductCategorySizeChart(ProductCategorySizeChartDTO productCategorySizeChartDTO);

    Integer deleteProductCategorySizeChart(Integer productCategorySizeChartId);
}
