package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.ProductSpecialSizeChartBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductSpecialSizeChartDTO;
import com.wearperfect.dataservice.api.dto.ProductSpecialSizeChartDetailsDTO;
import com.wearperfect.dataservice.api.service.ProductSpecialSizeChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductSpecialSizeChartController {

    @Autowired
    ProductSpecialSizeChartService productSpecialSizeChartService;

    @GetMapping(path = "/v1/productSpecialSizeCharts")
    List<ProductSpecialSizeChartBasicDetailsDTO> getProductSpecialSizeCharts() {
        return productSpecialSizeChartService.getProductSpecialSizeCharts();
    }

    @GetMapping(path = "/v1/productSpecialSizeCharts/{productSpecialSizeChartId}")
    ProductSpecialSizeChartDetailsDTO getProductSpecialSizeChartById(@PathVariable(name = "productSpecialSizeChartId", required = true) Long productSpecialSizeChartId) {
        return productSpecialSizeChartService.getProductSpecialSizeChartById(productSpecialSizeChartId);
    }

    @PostMapping(path = "/v1/productSpecialSizeCharts")
    ProductSpecialSizeChartBasicDetailsDTO createProductSpecialSizeChart(@RequestBody ProductSpecialSizeChartDTO productSpecialSizeChartDTO) {
        return productSpecialSizeChartService.createProductSpecialSizeChart(productSpecialSizeChartDTO);
    }

    @PutMapping(path = "/v1/productSpecialSizeCharts")
    ProductSpecialSizeChartBasicDetailsDTO updateProductSpecialSizeChart(@RequestBody ProductSpecialSizeChartDTO productSpecialSizeChartDTO) {
        return productSpecialSizeChartService.updateProductSpecialSizeChart(productSpecialSizeChartDTO);
    }

    @DeleteMapping(path = "/v1/productSpecialSizeCharts/{productSpecialSizeChartId}")
    Long deleteProductSpecialSizeChart(@PathVariable(name = "productSpecialSizeChartId", required = true) Long productSpecialSizeChartId) {
        return productSpecialSizeChartService.deleteProductSpecialSizeChart(productSpecialSizeChartId);
    }
}
