package com.wearperfect.dataservice.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.HashTag;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, Long>, JpaSpecificationExecutor<HashTag>{

	List<HashTag> findByTagLike(String tagSnippet);
}
