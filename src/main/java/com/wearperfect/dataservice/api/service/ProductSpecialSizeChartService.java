package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.ProductSpecialSizeChartBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductSpecialSizeChartDTO;
import com.wearperfect.dataservice.api.dto.ProductSpecialSizeChartDetailsDTO;

import java.util.List;

public interface ProductSpecialSizeChartService {

    List<ProductSpecialSizeChartBasicDetailsDTO> getProductSpecialSizeCharts();

    ProductSpecialSizeChartDetailsDTO getProductSpecialSizeChartById(Long productSpecialSizeChartId);

    ProductSpecialSizeChartBasicDetailsDTO createProductSpecialSizeChart(ProductSpecialSizeChartDTO productSpecialSizeChartDTO);

    ProductSpecialSizeChartBasicDetailsDTO updateProductSpecialSizeChart(ProductSpecialSizeChartDTO productSpecialSizeChartDTO);

    Long deleteProductSpecialSizeChart(Long productSpecialSizeChartId);
}
