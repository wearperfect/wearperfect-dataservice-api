package com.wearperfect.dataservice.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.SavedFilter;

@Repository
public interface SavedFilterRepository extends JpaRepository<SavedFilter, Long>, JpaSpecificationExecutor<SavedFilter>{

	List<SavedFilter> findByUserId(Long userId);
	
	SavedFilter findByIdAndUserId(Long id, Long userId);
}
