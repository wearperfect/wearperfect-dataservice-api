package com.wearperfect.dataservice.api.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.PostSave;

@Repository
public interface PostSaveRepository extends JpaRepository<PostSave, Long>, JpaSpecificationExecutor<PostSave>{

	PostSave findByPostIdAndSavedBy(Long postId, Long savedBy);
	
	List<PostSave> findBySavedBy(Long savedBy, Pageable page);
	
	void deleteByPostIdAndSavedBy(Long postId, Long savedBy);
}
