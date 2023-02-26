package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.*;

import java.util.List;

public interface ProductService {

    List<ProductBasicDetailsDTO> getProducts(ProductFilterDTO productFilter, Integer page, Integer size);

    List<ProductBasicDetailsDTO> searchProducts(String searchText, Integer page, Integer size);

    List<ProductBasicDetailsDTO> filterProducts(ProductFilterDTO productFilters, Integer page, Integer size);

    ProductBasicDetailsDTO getProductById(Long productId);

    List<ProductCategorySizeChartBasicDetailsDTO> getProductSizeCharts();
}
