package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.ProductMeasurementLabelBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ProductMeasurementLabelDTO;
import com.wearperfect.dataservice.api.entity.ProductMeasurementLabel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = { UtilityMapper.class, GenderCategoryMapper.class, ProductCategoryMapper.class, UserMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMeasurementLabelMapper {

    ProductMeasurementLabelDTO mapProductMeasurementLabelToProductMeasurementLabelDTO(ProductMeasurementLabel productMeasurementLabel);

    ProductMeasurementLabel mapProductMeasurementLabelDtoToProductMeasurementLabel(ProductMeasurementLabelDTO productMeasurementLabelDTO);

    ProductMeasurementLabelBasicDetailsDTO mapProductMeasurementLabelToProductMeasurementLabelBasicDetailsDto(ProductMeasurementLabel productMeasurementLabel);
}
