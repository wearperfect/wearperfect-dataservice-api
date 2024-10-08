package com.wearperfect.dataservice.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entity.UserSkill;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, Integer>, JpaSpecificationExecutor<UserSkill>{

	List<UserSkill> findByUserId(Long userId);
	
	void deleteByUserIdAndSkillId(Long userId, Integer skillId);
}
