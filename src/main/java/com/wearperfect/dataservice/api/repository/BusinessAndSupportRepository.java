package com.wearperfect.dataservice.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.BusinessAndSupport;

@Repository
public interface BusinessAndSupportRepository
		extends JpaRepository<BusinessAndSupport, Long>, JpaSpecificationExecutor<BusinessAndSupport> {

	Optional<BusinessAndSupport> findByIdAndUserId(Long id, Long userId);
	
	void deleteByIdAndUserId(Long id, Long userId);

}
