package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.entities.Post;
import com.wearperfect.dataservice.api.repositories.PostRepository;
import com.wearperfect.dataservice.api.service.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	PostRepository postRepository;

	@Override
	public List<Post> getPostsByUserId(Long userId) {
		// TODO Auto-generated method stub
		return postRepository.findAll();
	}

	@Override
	public Post postByUserId(Long userId, Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post createPost(Long userId, Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Long userId, Long postId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PostDTO likePost(Long userId, Long postId, Boolean isLiked) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDTO savePost(Long userId, Long postId, Boolean isSaved) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDTO commentPost(Long userId, Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDTO editPostComment(Long userId, Long postId, Long commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePostComment(Long userId, Long postId, Long commentId) {
		// TODO Auto-generated method stub
		
	}

}
