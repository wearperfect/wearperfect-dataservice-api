package com.wearperfect.dataservice.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.wearperfect.dataservice.api.dto.UserSkillBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserSkillDTO;
import com.wearperfect.dataservice.api.entity.UserSkill;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserSkillMapper {

	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "dateToTimeConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "dateToTimeConverter")
	UserSkillDTO mapUserSkillToUserSkillDto(UserSkill userSkill);
	
	@Mapping(source = "createdOn", target = "createdOn", qualifiedByName = "timeToDateConverter")
	@Mapping(source = "lastUpdatedOn", target = "lastUpdatedOn", qualifiedByName = "timeToDateConverter")
	UserSkill mapUserSkillDtoToUserSkill(UserSkillDTO userSkillDto);
	
	UserSkillBasicDetailsDTO mapUserSkillToUserSkillBasicDetailsDto(UserSkill userSkill);
}
