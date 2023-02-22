package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.RoleFeatureDTO;
import com.wearperfect.dataservice.api.dto.RoleFeatureDetailsDTO;
import com.wearperfect.dataservice.api.entity.RoleFeature;

@Mapper(uses = {UtilityMapper.class, FeatureMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleFeatureMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	RoleFeatureDTO mapFeatureToFeatureDto(RoleFeature roleFeature);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	RoleFeature mapFeatureDtoToFeature(RoleFeatureDTO roleFeatureDto);
	
	RoleFeatureDetailsDTO mapRoleFeatureToROleFeatureDetailsDto(RoleFeature roleFeature);
}
