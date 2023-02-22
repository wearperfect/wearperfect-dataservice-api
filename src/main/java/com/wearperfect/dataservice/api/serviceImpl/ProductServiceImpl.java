package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.constant.Pagination;
import com.wearperfect.dataservice.api.dto.ProductDTO;
import com.wearperfect.dataservice.api.dto.ProductFilterDTO;
import com.wearperfect.dataservice.api.entity.Product;
import com.wearperfect.dataservice.api.mapper.ProductMapper;
import com.wearperfect.dataservice.api.repository.ProductRepository;
import com.wearperfect.dataservice.api.service.ProductService;
import com.wearperfect.dataservice.api.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    EntityManager em;

    @Override
    public List<ProductDTO> getProducts(ProductFilterDTO productFilter, Integer page, Integer size) {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> productMapper.mapProductToProductDto(product)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> searchProducts(String searchText, Integer page, Integer size) {
        // This searches substring content in sequential order only.
        List<Product> products = productRepository.findByNameContainingIgnoreCase(
                searchText,
                PageRequest.of(
                        page != null? page : Pagination.PageNumber.DEFAULT.getValue(),
                        size != null? size : Pagination.PageSize.PRODUCTS.getValue())
        );
        // While returning group products by category and product category
        return products.stream().map(product -> productMapper.mapProductToProductDto(product)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> filterProducts(ProductFilterDTO productFilters, Integer page, Integer size) {
        List<Product> products = productRepository.findAll(
                ProductSpecification.filterProducts(productFilters),
                PageRequest.of(
                        page != null? page : Pagination.PageNumber.DEFAULT.getValue(),
                        size != null? size : Pagination.PageSize.PRODUCTS.getValue())
        ).getContent();
        return products.stream().map(product -> productMapper.mapProductToProductDto(product)).collect(Collectors.toList());
    }
}
