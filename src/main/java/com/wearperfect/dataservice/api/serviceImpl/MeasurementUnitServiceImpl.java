package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.dto.MeasurementUnitBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartDTO;
import com.wearperfect.dataservice.api.entity.MeasurementUnit;
import com.wearperfect.dataservice.api.mapper.MeasurementUnitMapper;
import com.wearperfect.dataservice.api.repository.MeasurementUnitRepository;
import com.wearperfect.dataservice.api.service.MeasurementUnitService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MeasurementUnitServiceImpl implements MeasurementUnitService {

    @Autowired
    MeasurementUnitRepository measurementUnitRepository;

    @Autowired
    MeasurementUnitMapper measurementUnitMapper;

    @Override
    public List<MeasurementUnitBasicDetailsDTO> getAllMeasurementUnits() {
        List<MeasurementUnit> measurementUnits = measurementUnitRepository.findAll();
        return measurementUnits
                .stream()
                .map(measurementUnit -> measurementUnitMapper.mapMeasurementUnitToMeasurementUnitBasicDetailsDto(measurementUnit))
                .collect(Collectors.toList());
    }

    @Override
    public MeasurementUnitBasicDetailsDTO getMeasurementUnitById(Integer measurementUnitId) {
        return null;
    }

    @Override
    public MeasurementUnitBasicDetailsDTO createMeasurementUnit(ProductCategorySizeChartDTO productCategorySizeChartDTO) {
        return null;
    }

    @Override
    public MeasurementUnitBasicDetailsDTO updateMeasurementUnit(ProductCategorySizeChartDTO productCategorySizeChartDTO) {
        return null;
    }

    @Override
    public Byte deleteMeasurementUnitById(Byte productCategorySizeChartId) {
        return null;
    }
}
