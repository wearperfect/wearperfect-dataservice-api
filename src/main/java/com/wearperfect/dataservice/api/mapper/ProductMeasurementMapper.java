package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ProductMeasurementBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductMeasurementDTO;
import com.wearperfect.dataservice.api.entity.ProductMeasurement;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = { UtilityMapper.class, ProductMeasurementLabelMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMeasurementMapper {

    ProductMeasurementDTO mapProductMeasurementToProductMeasurementDto(ProductMeasurement productMeasurement);

    ProductMeasurement mapProductMeasurementDtoToProductMeasurement(ProductMeasurementDTO productMeasurementDTO);

    ProductMeasurementBasicDetailsDTO mapProductMeasurementToProductMeasurementBasicDetailsDto(ProductMeasurement productMeasurement);
}
