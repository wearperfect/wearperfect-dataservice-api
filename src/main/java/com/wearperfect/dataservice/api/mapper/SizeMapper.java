package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.SizeDTO;
import com.wearperfect.dataservice.api.entity.Size;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SizeMapper {

    SizeDTO mapSizeToSizeDto(Size size);

    Size mapSizeDtoToSize(SizeDTO sizeDTO);
}
