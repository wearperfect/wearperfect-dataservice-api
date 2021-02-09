package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.SkillBasicDetailsDTO;
import com.wearperfect.dataservice.api.entities.Skill;
import com.wearperfect.dataservice.api.entities.Skill_;
import com.wearperfect.dataservice.api.entities.UserSkill;
import com.wearperfect.dataservice.api.mappers.SkillMapper;
import com.wearperfect.dataservice.api.repositories.SkillRepository;
import com.wearperfect.dataservice.api.repositories.UserSkillRepository;
import com.wearperfect.dataservice.api.service.SkillService;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

	@Autowired
	SkillRepository skillRepository;

	@Autowired
	UserSkillRepository userSkillRepository;

	@Autowired
	SkillMapper skillMapper;

	@Override
	public List<SkillBasicDetailsDTO> getSkills() {
		List<Skill> skills = skillRepository.findAll(Sort.by(Direction.ASC, Skill_.NAME));
		return skills.stream().map(skill -> skillMapper.mapSkillToSkillBasicDetailsDto(skill))
				.collect(Collectors.toList());
	}

	@Override
	public List<SkillBasicDetailsDTO> getUserSkills(Long userId) {
		List<UserSkill> userSkills = userSkillRepository.findByUserId(userId);
		List<Integer> userSkillIds = userSkills.stream().map(userSkill -> userSkill.getSkillId())
				.collect(Collectors.toList());
		List<Skill> skills = skillRepository.findByIdIn(userSkillIds);
		return skills.stream().map(skill -> skillMapper.mapSkillToSkillBasicDetailsDto(skill))
				.collect(Collectors.toList());
	}

}
