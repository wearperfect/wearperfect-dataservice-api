package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.RoleBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.RoleDTO;
import com.wearperfect.dataservice.api.dto.RoleDetailsDTO;
import com.wearperfect.dataservice.api.entity.Role;

@Mapper(uses= {UtilityMapper.class, RoleFeatureMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	RoleDTO mapRoleToRoleDto(Role role);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	Role mapRoleDtoToRole(RoleDTO roleDto);
	
	RoleDetailsDTO mapRoleToRoleDetailsDto(Role role);
	
	RoleBasicDetailsDTO mapRoleToRoleBasicDetailsDto(Role role);
}
