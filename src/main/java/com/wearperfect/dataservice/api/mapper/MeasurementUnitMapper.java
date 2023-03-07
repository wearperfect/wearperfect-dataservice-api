package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.MeasurementUnitBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.MeasurementUnitDTO;
import com.wearperfect.dataservice.api.entity.MeasurementUnit;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = { UtilityMapper.class, UserMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MeasurementUnitMapper {

    MeasurementUnit mapMeasurementUnitDtoToMeasurementUnit(MeasurementUnitDTO measurementUnitDTO);

    MeasurementUnitDTO mapMeasurementUnitToMeasurementUnitDto(MeasurementUnit measurementUnit);

    MeasurementUnitBasicDetailsDTO mapMeasurementUnitToMeasurementUnitBasicDetailsDto(MeasurementUnit measurementUnit);
}
