package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.ProductDTO;
import com.wearperfect.dataservice.api.dto.ProductFilterDTO;
import com.wearperfect.dataservice.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/v1/products")
    List<ProductDTO> getProducts(){
        return productService.getProducts(null, 0, 0);
    }

    @GetMapping("/v1/products/search/{searchText}")
    List<ProductDTO> searchProducts(
            @PathVariable(name = "searchText") String searchText,
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size
    ){
        return productService.searchProducts(searchText, page, size);
    }

    @PostMapping("/v1/products/filter")
    List<ProductDTO> filterProducts(
            @RequestBody ProductFilterDTO productFilters,
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size
    ){
        return productService.filterProducts(productFilters, page, size);
    }
}
