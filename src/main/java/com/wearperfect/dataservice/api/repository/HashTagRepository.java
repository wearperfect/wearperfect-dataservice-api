package com.wearperfect.dataservice.api.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.HashTag;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, Long>, JpaSpecificationExecutor<HashTag>{

	List<HashTag> findByTagLike(String tagSnippet);
	
	List<HashTag> findByTagIn(Set<String> hashTags);
}
