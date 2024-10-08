package com.wearperfect.dataservice.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entity.PreferenceFilter;

@Repository
public interface PreferenceFilterRepository extends JpaRepository<PreferenceFilter, Long>, JpaSpecificationExecutor<PreferenceFilter>{

	List<PreferenceFilter> findByUserId(Long userId);
	
	PreferenceFilter findByIdAndUserId(Long id, Long userId);
}
