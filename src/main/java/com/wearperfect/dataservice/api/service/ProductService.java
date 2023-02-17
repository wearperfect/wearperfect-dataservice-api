package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.ProductDTO;
import com.wearperfect.dataservice.api.dto.ProductFilterDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getProducts(ProductFilterDTO productFilter, Integer page, Integer size);
}
