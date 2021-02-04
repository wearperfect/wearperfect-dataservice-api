package com.wearperfect.dataservice.api.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.PostLike;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long>, JpaSpecificationExecutor<PostLike>{

	
	Long countByPostId(Long postId);

	List<PostLike> findByPostId(Long postId, Pageable page);
	
	List<PostLike> findByLikedBy(Long likedBy, Pageable page);

	PostLike findByPostIdAndLikedBy(Long postId, Long likedBy);
	
	void deleteByPostIdAndLikedBy(Long postId, Long likedBy);
	
}
