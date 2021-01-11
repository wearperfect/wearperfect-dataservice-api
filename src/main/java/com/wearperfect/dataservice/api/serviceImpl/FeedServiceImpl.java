package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.entities.Post;
import com.wearperfect.dataservice.api.repositories.PostRepository;
import com.wearperfect.dataservice.api.service.FeedService;

@Service
public class FeedServiceImpl implements FeedService{
	
	@Autowired
	PostRepository postRepository;

	@Override
	public List<Post> getFeed() {
		// TODO Auto-generated method stub
		return postRepository.findAll();
	}

	@Override
	public List<Post> getFeedByUserId(Long userId) {
		// TODO Auto-generated method stub
		return postRepository.findAll();
	}

}
