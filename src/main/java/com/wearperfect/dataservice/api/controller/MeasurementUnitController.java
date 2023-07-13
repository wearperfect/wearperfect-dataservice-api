package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.MeasurementUnitBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartDTO;
import com.wearperfect.dataservice.api.dto.ProductCategorySizeChartDetailsDTO;
import com.wearperfect.dataservice.api.service.MeasurementUnitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MeasurementUnitController {

    MeasurementUnitService measurementUnitService;
    
    public MeasurementUnitController(MeasurementUnitService measurementUnitService){
        this.measurementUnitService = measurementUnitService;
    }

    @GetMapping(path = "/v1/measurementUnits")
    List<MeasurementUnitBasicDetailsDTO> getAllMeasurementUnits() {
        return measurementUnitService.getAllMeasurementUnits();
    }

    @GetMapping(path = "/v1/measurementUnits/{measurementUnitId}")
    MeasurementUnitBasicDetailsDTO getMeasurementUnitById(@PathVariable(name = "measurementUnitId") Integer measurementUnitId) {
        return measurementUnitService.getMeasurementUnitById(measurementUnitId);
    }

    @PostMapping(path = "/v1/measurementUnits")
    MeasurementUnitBasicDetailsDTO createMeasurementUnit(@RequestBody ProductCategorySizeChartDTO productCategorySizeChartDTO) {
        return measurementUnitService.createMeasurementUnit(productCategorySizeChartDTO);
    }

    @PutMapping(path = "/v1/measurementUnits")
    MeasurementUnitBasicDetailsDTO updateMeasurementUnit(@RequestBody ProductCategorySizeChartDTO productCategorySizeChartDTO) {
        return measurementUnitService.updateMeasurementUnit(productCategorySizeChartDTO);
    }

    @DeleteMapping(path = "/v1/measurementUnits/{measurementUnitId}")
    Byte deleteMeasurementUnitById(@PathVariable(name = "measurementUnitId", required = true) Byte productCategorySizeChartId) {
        return measurementUnitService.deleteMeasurementUnitById(productCategorySizeChartId);
    }
}
