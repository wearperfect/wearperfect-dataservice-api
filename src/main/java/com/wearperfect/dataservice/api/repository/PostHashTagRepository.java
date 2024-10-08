package com.wearperfect.dataservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entity.PostHashTag;

@Repository
public interface PostHashTagRepository extends JpaRepository<PostHashTag, Long>, JpaSpecificationExecutor<PostHashTag>{
	
	Long countByHashTagId(Long hashTagId);
}
