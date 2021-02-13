package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.SkillBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserSkillsResponseDTO;

public interface SkillService {

	List<SkillBasicDetailsDTO> getSkills();

	UserSkillsResponseDTO getUserSkills(Long userId);

	UserSkillsResponseDTO saveUserSkill(Long userId, Integer skillId);

	UserSkillsResponseDTO deleteUserSkill(Long userId, Integer skillId);
}
