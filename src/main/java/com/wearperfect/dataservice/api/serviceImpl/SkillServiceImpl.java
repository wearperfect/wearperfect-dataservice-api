package com.wearperfect.dataservice.api.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.dto.SkillBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserSkillsResponseDTO;
import com.wearperfect.dataservice.api.entities.Skill;
import com.wearperfect.dataservice.api.entities.Skill_;
import com.wearperfect.dataservice.api.entities.UserSkill;
import com.wearperfect.dataservice.api.mappers.SkillMapper;
import com.wearperfect.dataservice.api.repository.SkillRepository;
import com.wearperfect.dataservice.api.repository.UserSkillRepository;
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
	public UserSkillsResponseDTO getUserSkills(Long userId) {
		List<UserSkill> userSkills = userSkillRepository.findByUserId(userId);
		List<Integer> userSkillIds = userSkills.stream().map(userSkill -> userSkill.getSkillId())
				.collect(Collectors.toList());
		List<Skill> skills = skillRepository.findByIdIn(userSkillIds, Sort.by(Direction.ASC, Skill_.NAME));
		List<SkillBasicDetailsDTO> skillDtoList = skills.stream().map(skill -> skillMapper.mapSkillToSkillBasicDetailsDto(skill))
		.collect(Collectors.toList());
		return new UserSkillsResponseDTO(userId, skillDtoList);
	}

	@Override
	public UserSkillsResponseDTO saveUserSkill(Long userId, Integer skillId) {
		UserSkill userSkill = new UserSkill();
		userSkill.setActive(true);
		userSkill.setCreatedBy(userId);
		userSkill.setCreatedOn(new Date());
		userSkill.setSkillId(skillId);
		userSkill.setUserId(userId);
		userSkillRepository.save(userSkill);
		Optional<Skill> skill = skillRepository.findById(skillId);
		return getUserSkillsResponseDTO(userId, skillId, skill);
	}

	@Override
	public UserSkillsResponseDTO deleteUserSkill(Long userId, Integer skillId) {
		userSkillRepository.deleteByUserIdAndSkillId(userId, skillId);
		Optional<Skill> skill = skillRepository.findById(skillId);
		return getUserSkillsResponseDTO(userId, skillId, skill);
	}
	
	UserSkillsResponseDTO getUserSkillsResponseDTO(Long userId, Integer skillId, Optional<Skill> skill) {
		List<SkillBasicDetailsDTO> skillsDtoList = new ArrayList<>();
		skillsDtoList.add(skillMapper.mapSkillToSkillBasicDetailsDto(skill.get()));
		if(skill.isPresent()) {
			return new UserSkillsResponseDTO(userId, skillsDtoList);
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Skill not found with id "+skillId);
		}
	}

}
