package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.dto.ProductDetailsDTO;
import com.wearperfect.dataservice.api.repository.ProductRepository;
import com.wearperfect.dataservice.api.service.ProductCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCreationServiceImpl implements ProductCreationService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductDetailsDTO createProduct(ProductDetailsDTO productDetailsDTO) {
        // Save Product
        // Save Product Media
        // Save Product Category Size Chart if not present
        // Save Product Category Sizes if not present
        // Save Product Special Sizes if not present
        // Save Product Measurements
        return null;
    }
}
