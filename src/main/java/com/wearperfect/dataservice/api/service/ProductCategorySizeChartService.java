package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartDTO;
import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartDetailsDTO;

import java.util.List;

public interface ProductCategorySizeChartService {
    List<ProductCategorySizeChartBasicDetailsDTO> getProductCategorySizeCharts();

    ProductCategorySizeChartDetailsDTO getProductCategorySizeChartById(Integer productCategorySizeChartId);

    ProductCategorySizeChartBasicDetailsDTO createProductCategorySizeChart(ProductCategorySizeChartDTO productCategorySizeChartDTO);

    ProductCategorySizeChartBasicDetailsDTO updateProductCategorySizeChart(ProductCategorySizeChartDTO productCategorySizeChartDTO);

    Integer deleteProductCategorySizeChart(Integer productCategorySizeChartId);
}
