package com.wearperfect.dataservice.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entity.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long>, JpaSpecificationExecutor<Work> {
	
	List<Work> findByWorkedBy(Long workedBy, Pageable page);
	
	Work findByIdAndWorkedBy(Long id, Long workedBy);
	
	void deleteByIdAndWorkedBy(Long id, Long workedBy);
	
}
