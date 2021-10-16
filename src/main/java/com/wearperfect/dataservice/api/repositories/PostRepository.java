package com.wearperfect.dataservice.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post>{
	
	Long countByCreatedBy(Long userId);
	
	Optional<Post> findByIdAndCreatedBy(Long postId, Long userId);
	
	List<Post> findByCreatedBy(Long userId, Pageable page);
	
	List<Post> findByIdIn(List<Long> postIdList, Pageable page);
}

