package com.wearperfect.dataservice.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.PostHashTag;

@Repository
public interface PostHashTagRepository extends JpaRepository<PostHashTag, Long>, JpaSpecificationExecutor<PostHashTag>{
	
	Long countByHashTagId(Long hashTagId);
}
