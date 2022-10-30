package com.wearperfect.dataservice.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wearperfect.dataservice.api.entities.PostComment;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long>, JpaSpecificationExecutor<PostComment>{
	
	List<PostComment> findByPostId(Long postId, Pageable page);
	
	PostComment findByIdAndPostIdAndCommentedBy(Long commentId, Long postId, Long commentedBy);
	
	Long countByPostId(Long postId);
	
}
