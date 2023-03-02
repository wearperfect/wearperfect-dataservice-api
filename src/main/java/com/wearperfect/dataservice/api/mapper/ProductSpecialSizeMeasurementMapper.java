package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ProductSpecialSizeMeasurementBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductSpecialSizeMeasurementDTO;
import com.wearperfect.dataservice.api.entity.ProductSpecialSizeMeasurement;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = { UtilityMapper.class, ProductMeasurementLabelMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductSpecialSizeMeasurementMapper {

    ProductSpecialSizeMeasurement mapProductSpecialSizeMeasurementDtoToProductSpecialSizeMeasurement(ProductSpecialSizeMeasurementDTO productSpecialSizeMeasurementDTO);

    ProductSpecialSizeMeasurementDTO mapProductSpecialSizeMeasurementToProductSpecialSizeMeasurementDto(ProductSpecialSizeMeasurement productSpecialSizeMeasurement);

    ProductSpecialSizeMeasurementBasicDetailsDTO mapProductSpecialSizeMeasurementToProductSpecialSizeMeasurementBasicDetailsDto(ProductSpecialSizeMeasurement productSpecialSizeMeasurement);
}
