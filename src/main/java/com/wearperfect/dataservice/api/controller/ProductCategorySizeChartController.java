package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartDTO;
import com.wearperfect.dataservice.api.dto.UserSkillsResponseDTO;
import com.wearperfect.dataservice.api.service.ProductCategorySizeChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductCategorySizeChartController {

    @Autowired
    ProductCategorySizeChartService productCategorySizeChartService;

    @GetMapping(path = "/v1/productCategorySizeCharts")
    List<ProductCategorySizeChartBasicDetailsDTO> getProductCategorySizeCharts() {
        return productCategorySizeChartService.getProductCategorySizeCharts();
    }

    @GetMapping(path = "/v1/productCategorySizeCharts/{productCategorySizeChartId}")
    ProductCategorySizeChartBasicDetailsDTO getProductCategorySizeChartById(@PathVariable(name = "productCategorySizeChartId", required = true) Integer productCategorySizeChartId) {
        return productCategorySizeChartService.getProductCategorySizeChartById(productCategorySizeChartId);
    }

    @PostMapping(path = "/v1/productCategorySizeCharts")
    ProductCategorySizeChartBasicDetailsDTO createProductCategorySizeChart(@RequestBody ProductCategorySizeChartDTO productCategorySizeChartDTO) {
        return productCategorySizeChartService.createProductCategorySizeChart(productCategorySizeChartDTO);
    }

    @PutMapping(path = "/v1/productCategorySizeCharts")
    ProductCategorySizeChartBasicDetailsDTO updateProductCategorySizeChart(@RequestBody ProductCategorySizeChartDTO productCategorySizeChartDTO) {
        return productCategorySizeChartService.updateProductCategorySizeChart(productCategorySizeChartDTO);
    }

    @DeleteMapping(path = "/v1/productCategorySizeCharts/{productCategorySizeChartId}")
    Integer deleteProductCategorySizeChart(@PathVariable(name = "productCategorySizeChartId", required = true) Integer productCategorySizeChartId) {
        return productCategorySizeChartService.deleteProductCategorySizeChart(productCategorySizeChartId);
    }
}
