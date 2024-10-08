package com.wearperfect.dataservice.api.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entity.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer>, JpaSpecificationExecutor<Skill>{

	List<Skill> findByIdIn(List<Integer> skills, Sort sort);
}
