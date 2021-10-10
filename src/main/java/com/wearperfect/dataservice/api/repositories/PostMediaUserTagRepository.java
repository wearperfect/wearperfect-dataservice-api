package com.wearperfect.dataservice.api.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.PostMediaUserTag;
import com.wearperfect.dataservice.api.entities.PostUserMention;

@Repository
public interface PostMediaUserTagRepository extends JpaRepository<PostMediaUserTag, Long>, JpaSpecificationExecutor<PostMediaUserTag>{

	List<PostMediaUserTag> findByTaggedUserId(Long taggedUserId, Pageable page);
}
