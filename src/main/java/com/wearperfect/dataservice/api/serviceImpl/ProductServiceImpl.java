package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.dto.ProductDTO;
import com.wearperfect.dataservice.api.dto.ProductFilterDTO;
import com.wearperfect.dataservice.api.entities.Product;
import com.wearperfect.dataservice.api.mappers.ProductMapper;
import com.wearperfect.dataservice.api.repository.ProductRepository;
import com.wearperfect.dataservice.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<ProductDTO> getProducts(ProductFilterDTO productFilter, Integer page, Integer size) {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> productMapper.mapProductToProduct(product)).collect(Collectors.toList());
    }
}
