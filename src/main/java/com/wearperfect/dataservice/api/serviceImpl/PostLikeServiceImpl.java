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
import com.wearperfect.dataservice.api.entity.PostLike;
import com.wearperfect.dataservice.api.entity.PostLike_;
import com.wearperfect.dataservice.api.mapper.PostLikeMapper;
import com.wearperfect.dataservice.api.repository.PostLikeRepository;
import com.wearperfect.dataservice.api.service.PostLikeService;

@Service
@Transactional
public class PostLikeServiceImpl implements PostLikeService {

	@Autowired
	PostLikeRepository postLikeRepository;

	@Autowired
	PostLikeMapper postLikeMapper;

	@Override
	public List<PostLikeDTO> postLikes(Long postId) {
		List<PostLike> postLikes = postLikeRepository.findByPostId(postId,
				PageRequest.of(0, 10, Sort.by(PostLike_.LIKED_ON).descending()));
		return postLikes.stream().map(postLike -> postLikeMapper.mapPostLikeToPostLikeDto(postLike))
				.collect(Collectors.toList());
	}

	@Override
	public Boolean isPostLikedByUserId(Long userId, Long postId) {
		Optional<PostLike> like = Optional.ofNullable(postLikeRepository.findByPostIdAndLikedBy(postId, userId));
		if (like.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public Long likePost(Long userId, Long postId) {

		Optional<PostLike> existingPostLike = Optional
				.ofNullable(postLikeRepository.findByPostIdAndLikedBy(postId, userId));

		if (existingPostLike.isPresent()) {
			return postId;
		}

		PostLike like = new PostLike();
		like.setPostId(postId);
		like.setLikedBy(userId);
		like.setLikedOn(new Date());
		PostLike postLike = postLikeRepository.save(like);
		return postId;
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
