package com.wearperfect.dataservice.api.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.PostUserTag;

@Repository
public interface PostUserTagRepository extends JpaRepository<PostUserTag, Long>, JpaSpecificationExecutor<PostUserTag>{

	List<PostUserTag> findByTaggedUserId(Long taggedUserId, Pageable page);
}
