package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.MeasurementUnitBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartDTO;

import java.util.List;

public interface MeasurementUnitService {
    List<MeasurementUnitBasicDetailsDTO> getAllMeasurementUnits();

    MeasurementUnitBasicDetailsDTO getMeasurementUnitById(Integer measurementUnitId);

    MeasurementUnitBasicDetailsDTO createMeasurementUnit(ProductCategorySizeChartDTO productCategorySizeChartDTO);

    MeasurementUnitBasicDetailsDTO updateMeasurementUnit(ProductCategorySizeChartDTO productCategorySizeChartDTO);

    Byte deleteMeasurementUnitById(Byte productCategorySizeChartId);
}
