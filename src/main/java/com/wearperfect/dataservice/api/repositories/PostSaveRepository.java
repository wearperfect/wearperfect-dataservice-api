package com.wearperfect.dataservice.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.PostSave;

@Repository
public interface PostSaveRepository extends JpaRepository<PostSave, Long>, JpaSpecificationExecutor<PostSave>{

	PostSave findByPostIdAndSavedBy(Long postId, Long savedBy);
	
	void deleteByPostIdAndSavedBy(Long postId, Long savedBy);
}
