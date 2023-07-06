package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartDTO;
import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartDetailsDTO;
import com.wearperfect.dataservice.api.entity.ProductCategorySizeChart;
import com.wearperfect.dataservice.api.mapper.ProductCategorySizeChartMapper;
import com.wearperfect.dataservice.api.repository.ProductCategorySizeChartRepository;
import com.wearperfect.dataservice.api.service.ProductCategorySizeChartService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductCategorySizeChartServiceImpl implements ProductCategorySizeChartService {

    @Autowired
    ProductCategorySizeChartRepository productCategorySizeChartRepository;

    @Autowired
    ProductCategorySizeChartMapper productCategorySizeChartMapper;

    @Override
    public List<ProductCategorySizeChartBasicDetailsDTO> getProductCategorySizeCharts() {
        List<ProductCategorySizeChart> productCategorySizeChartList = productCategorySizeChartRepository.findAll();
        return productCategorySizeChartList
                .stream()
                .map(productCategorySizeChart -> productCategorySizeChartMapper.mapProductCategorySizeChartToProductCategorySizeChartBasicDetailsDto(productCategorySizeChart))
                .toList();
    }

    @Override
    public ProductCategorySizeChartDetailsDTO getProductCategorySizeChartById(Integer productCategorySizeChartId) {
        Optional<ProductCategorySizeChart> productCategorySizeChart = productCategorySizeChartRepository.findById(productCategorySizeChartId);
        if(productCategorySizeChart.isPresent()){
            return productCategorySizeChartMapper.mapProductCategorySizeChartToProductCategorySizeChartDetailsDto(productCategorySizeChart.get());
        } else {
            throw new EntityNotFoundException("Requested Size Chart is not found.");
        }
    }

    @Override
    public ProductCategorySizeChartBasicDetailsDTO createProductCategorySizeChart(ProductCategorySizeChartDTO productCategorySizeChartDTO) {
        return null;
    }

    @Override
    public ProductCategorySizeChartBasicDetailsDTO updateProductCategorySizeChart(ProductCategorySizeChartDTO productCategorySizeChartDTO) {
        return null;
    }

    @Override
    public Integer deleteProductCategorySizeChart(Integer productCategorySizeChartId) {
        try {
            productCategorySizeChartRepository.deleteById(productCategorySizeChartId);
        } catch (Exception e){
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete the requested Product Category Size Chart with ID "+productCategorySizeChartId+".");
        }
        return productCategorySizeChartId;
    }
}
