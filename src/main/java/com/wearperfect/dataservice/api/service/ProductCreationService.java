package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.ProductDetailsDTO;

public interface ProductCreationService {

    ProductDetailsDTO createProduct(ProductDetailsDTO productDetailsDTO);
}
