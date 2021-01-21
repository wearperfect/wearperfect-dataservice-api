package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.entities.Post;
import com.wearperfect.dataservice.api.entities.PostLike;
import com.wearperfect.dataservice.api.entities.PostSave;
import com.wearperfect.dataservice.api.mappers.PostDetailsMapper;
import com.wearperfect.dataservice.api.repositories.PostLikeRepository;
import com.wearperfect.dataservice.api.repositories.PostRepository;
import com.wearperfect.dataservice.api.repositories.PostSaveRepository;
import com.wearperfect.dataservice.api.service.FeedService;
import com.wearperfect.dataservice.api.specifications.PostDetailsSpecification;

@Service
@Transactional
public class FeedServiceImpl implements FeedService{
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	PostDetailsMapper postDetailsMapper;
	
	@Autowired
	PostLikeRepository postLikeRepository;
	
	@Autowired
	PostSaveRepository postSaveRepository;

	@Override
	public List<PostDetailsDTO> getFeed() {
		List<Post> posts = postRepository.findAll();
		List<PostDetailsDTO> postDetailsList = posts.stream().map(post -> postDetailsMapper.mapPostToPostDetailsDto(post)).collect(Collectors.toList());
		postDetailsList.forEach(post->{
			post.setTotalLikes(postLikeRepository.countByPostId(post.getId()));
		});
		return postDetailsList;
	}

	@Override
	public List<PostDetailsDTO> getFeedByUserId(Long userId) {
		List<Post> posts = postRepository.findAll(PostDetailsSpecification.postsByUserIdPredicate(userId));
		List<PostDetailsDTO> postDetailsList = posts.stream().map(post -> postDetailsMapper.mapPostToPostDetailsDto(post)).collect(Collectors.toList());
		postDetailsList.forEach(post->{
			post.setTotalLikes(postLikeRepository.countByPostId(post.getId()));
			Optional<PostLike> like = Optional.ofNullable(postLikeRepository.findByPostIdAndLikedBy(post.getId(), userId));
			if(like.isPresent() && like.get().getLikedBy() == userId) {
				post.setIsLiked(true);
			}else {
				post.setIsLiked(false);
			}
			Optional<PostSave> save = Optional.ofNullable(postSaveRepository.findByPostIdAndSavedBy(post.getId(), userId));
			if(save.isPresent() && save.get().getSavedBy() == userId) {
				post.setIsSaved(true);
			}else {
				post.setIsSaved(false);
			}
		});
		return postDetailsList;
	}

}
