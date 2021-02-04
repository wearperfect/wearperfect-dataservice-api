package com.wearperfect.dataservice.api.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long>, JpaSpecificationExecutor<Work> {
	
	List<Work> findByWorkedBy(Long workedBy, Pageable page);
	
}
