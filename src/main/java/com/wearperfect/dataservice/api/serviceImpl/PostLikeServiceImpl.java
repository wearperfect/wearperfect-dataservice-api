package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.PostLikeDTO;
import com.wearperfect.dataservice.api.entities.PostLike;
import com.wearperfect.dataservice.api.entities.PostLike_;
import com.wearperfect.dataservice.api.mappers.PostLikeMapper;
import com.wearperfect.dataservice.api.repositories.PostLikeRepository;
import com.wearperfect.dataservice.api.service.PostLikeService;

@Service
@Transactional
public class PostLikeServiceImpl implements  PostLikeService{
	
	@Autowired
	PostLikeRepository postLikeRepository;
	
	@Autowired
	PostLikeMapper postLikeMapper;

	@Override
	public List<PostLikeDTO> postLikes(Long userId, Long postId) {
		List<PostLike> postLikes = postLikeRepository.findByPostId(postId, PageRequest.of(0, 10, Sort.by(PostLike_.LIKED_ON).descending()));
		return postLikes.stream().map(postLike -> postLikeMapper.mapPostLikeToPostLikeDto(postLike)).collect(Collectors.toList());
	}

	@Override
	public PostLikeDTO likePost(Long userId, Long postId) {

		Optional<PostLike> existingPostLike = Optional
				.ofNullable(postLikeRepository.findByPostIdAndLikedBy(postId, userId));

		if (existingPostLike.isPresent()) {
			return postLikeMapper.mapPostLikeToPostLikeDto(existingPostLike.get());
		}

		PostLike like = new PostLike();
		like.setPostId(postId);
		like.setLikedBy(userId);
		like.setLikedOn(new Date());
		PostLike postLike = postLikeRepository.save(like);
		return postLikeMapper.mapPostLikeToPostLikeDto(postLike);
	}

	@Override
	public Long unLikePost(Long userId, Long postId) {
		Optional<PostLike> existingPostLike = Optional
				.ofNullable(postLikeRepository.findByPostIdAndLikedBy(postId, userId));

		if (existingPostLike.isPresent()) {
			postLikeRepository.deleteByPostIdAndLikedBy(postId, userId);
		}
		return postId;
	}
}
