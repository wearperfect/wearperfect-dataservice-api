package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.dto.ProductSpecialSizeChartBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductSpecialSizeChartDTO;
import com.wearperfect.dataservice.api.dto.ProductSpecialSizeChartDetailsDTO;
import com.wearperfect.dataservice.api.entity.ProductSpecialSizeChart;
import com.wearperfect.dataservice.api.mapper.ProductSpecialSizeChartMapper;
import com.wearperfect.dataservice.api.repository.ProductSpecialSizeChartRepository;
import com.wearperfect.dataservice.api.service.ProductSpecialSizeChartService;
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
public class ProductSpecialSizeChartServiceImpl implements ProductSpecialSizeChartService {

    @Autowired
    ProductSpecialSizeChartRepository productSpecialSizeChartRepository;

    @Autowired
    ProductSpecialSizeChartMapper productSpecialSizeChartMapper;

    @Override
    public List<ProductSpecialSizeChartBasicDetailsDTO> getProductSpecialSizeCharts() {
        List<ProductSpecialSizeChart> productSpecialSizeChartList = productSpecialSizeChartRepository.findAll();
        return productSpecialSizeChartList
                .stream()
                .map(productSpecialSizeChart -> productSpecialSizeChartMapper.mapProductSpecialSizeChartToProductSpecialSizeChartBasicDetailsDto(productSpecialSizeChart))
                .toList();
    }

    @Override
    public ProductSpecialSizeChartDetailsDTO getProductSpecialSizeChartById(Long productSpecialSizeChartId) {
        Optional<ProductSpecialSizeChart> productSpecialSizeChart = productSpecialSizeChartRepository.findById(productSpecialSizeChartId);
        if(productSpecialSizeChart.isPresent()){
            return productSpecialSizeChartMapper.mapProductSpecialSizeChartToProductSpecialSizeChartDetailsDto(productSpecialSizeChart.get());
        } else {
            throw new EntityNotFoundException("Requested Size Chart is not found.");
        }
    }

    @Override
    public ProductSpecialSizeChartBasicDetailsDTO createProductSpecialSizeChart(ProductSpecialSizeChartDTO productSpecialSizeChartDTO) {
        return null;
    }

    @Override
    public ProductSpecialSizeChartBasicDetailsDTO updateProductSpecialSizeChart(ProductSpecialSizeChartDTO productSpecialSizeChartDTO) {
        return null;
    }

    @Override
    public Long deleteProductSpecialSizeChart(Long productSpecialSizeChartId) {
        try {
            productSpecialSizeChartRepository.deleteById(productSpecialSizeChartId);
        } catch (Exception e){
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete the requested Product Special Size Chart with ID "+productSpecialSizeChartId+".");
        }
        return productSpecialSizeChartId;
    }
}
