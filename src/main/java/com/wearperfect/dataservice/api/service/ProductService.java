package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.*;

import java.util.List;

public interface ProductService {

    List<ProductDetailsDTO> getProducts(ProductFilterDTO productFilter, Integer page, Integer size);

    List<ProductDetailsDTO> searchProducts(String searchText, Integer page, Integer size);

    List<ProductDetailsDTO> filterProducts(ProductFilterDTO productFilters, Integer page, Integer size);

    ProductDetailsDTO getProductById(Long productId);

    List<ProductCategorySizeChartBasicDetailsDTO> getProductSizeCharts();
}
