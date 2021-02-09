package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.SkillBasicDetailsDTO;

public interface SkillService {

	List<SkillBasicDetailsDTO> getSkills();

	List<SkillBasicDetailsDTO> getUserSkills(Long userId);
}
