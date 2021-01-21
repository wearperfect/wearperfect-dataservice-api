package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.entities.Post;
import com.wearperfect.dataservice.api.mappers.PostDetailsMapper;
import com.wearperfect.dataservice.api.repositories.PostRepository;
import com.wearperfect.dataservice.api.service.FeedService;
import com.wearperfect.dataservice.api.specifications.PostDetailsSpecification;

@Service
public class FeedServiceImpl implements FeedService{
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	PostDetailsMapper postDetailsMapper;

	@Override
	public List<PostDetailsDTO> getFeed() {
		List<Post> posts = postRepository.findAll(PostDetailsSpecification.postsByUserIdPredicate(4));
		return posts.stream().map(post->postDetailsMapper.mapPostToPostDetailsDto(post)).collect(Collectors.toList());
	}

	@Override
	public List<PostDetailsDTO> getFeedByUserId(Long userId) {
		List<Post> posts = postRepository.findAll(PostDetailsSpecification.postsByUserIdPredicate(userId));
		return posts.stream().map(post->postDetailsMapper.mapPostToPostDetailsDto(post)).collect(Collectors.toList());
	}

}
