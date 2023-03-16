package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.*;
import com.wearperfect.dataservice.api.entity.ProductCategorySizeChart;
import com.wearperfect.dataservice.api.mapper.ProductCategorySizeChartMapper;
import com.wearperfect.dataservice.api.repository.ProductCategorySizeChartRepository;
import com.wearperfect.dataservice.api.service.ProductCreationService;
import com.wearperfect.dataservice.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductCreationService productCreationService;

    @GetMapping("/v1/products")
    List<ProductBasicDetailsDTO> getProducts(){
        return productService.getProducts(null, 0, 0);
    }

    @GetMapping("/v1/products/{productId}")
    ProductBasicDetailsDTO getProductById(@PathVariable(name = "productId") Long productId){
        return productService.getProductById(productId);
    }

    @PostMapping("/v1/products")
    ResponseEntity<?> createProduct(@RequestBody ProductBasicDetailsDTO productBasicDetailsDTO){
        ProductBasicDetailsDTO createdProduct = productCreationService.createProduct(productBasicDetailsDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.OK);
    }

    @GetMapping("/v1/products/size-charts")
    List<ProductCategorySizeChartBasicDetailsDTO> getProductSizeCharts(){
        return productService.getProductSizeCharts();
    }

    @GetMapping("/v1/products/search/{searchText}")
    List<ProductBasicDetailsDTO> searchProducts(
            @PathVariable(name = "searchText") String searchText,
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size
    ){
        return productService.searchProducts(searchText, page, size);
    }

    @PostMapping("/v1/products/filter")
    List<ProductBasicDetailsDTO> filterProducts(
            @RequestBody ProductFilterDTO productFilters,
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size
    ){
        return productService.filterProducts(productFilters, page, size);
    }
}
