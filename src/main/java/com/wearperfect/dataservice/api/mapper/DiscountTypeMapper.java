package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.DiscountTypeBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.DiscountTypeDTO;
import com.wearperfect.dataservice.api.entity.DiscountType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiscountTypeMapper {

    DiscountType mapDiscountTypeDtoToDiscountType(DiscountTypeDTO discountTypeDTO);

    DiscountTypeDTO mapDiscountTypeToDiscountTypeDto(DiscountType discountType);

    DiscountTypeBasicDetailsDTO mapDiscountTypeToDiscountTypeBasicDetailsDto(DiscountType discountType);
}
