package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.ProductDTO;
import com.wearperfect.dataservice.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/v1/products")
    List<ProductDTO> getProducts(){
        return productService.getProducts(null, 0, 0);
    }

}
