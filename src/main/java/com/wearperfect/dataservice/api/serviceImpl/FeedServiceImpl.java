package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.constant.Pagination;
import com.wearperfect.dataservice.api.dto.PostCommentDetailsDTO;
import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.entity.Post;
import com.wearperfect.dataservice.api.entity.PostComment;
import com.wearperfect.dataservice.api.entity.PostComment_;
import com.wearperfect.dataservice.api.entity.Post_;
import com.wearperfect.dataservice.api.mapper.PostCommentMapper;
import com.wearperfect.dataservice.api.mapper.PostMapper;
import com.wearperfect.dataservice.api.repository.FollowRepository;
import com.wearperfect.dataservice.api.repository.PostCommentRepository;
import com.wearperfect.dataservice.api.repository.PostLikeRepository;
import com.wearperfect.dataservice.api.repository.PostRepository;
import com.wearperfect.dataservice.api.repository.PostSaveRepository;
import com.wearperfect.dataservice.api.service.FeedService;
import com.wearperfect.dataservice.api.service.FollowService;
import com.wearperfect.dataservice.api.service.PostLikeService;
import com.wearperfect.dataservice.api.service.PostSaveService;

@Service
@Transactional
public class FeedServiceImpl implements FeedService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	PostLikeRepository postLikeRepository;

	@Autowired
	PostSaveRepository postSaveRepository;

	@Autowired
	PostCommentRepository postCommentRepository;

	@Autowired
	FollowRepository followRepository;

	@Autowired
	PostMapper postMapper;
	
	@Autowired
	PostLikeService postLikeService;
	
	@Autowired
	PostSaveService postSaveService;
	
	@Autowired
	FollowService followService;

	@Autowired
	PostCommentMapper postCommentMapper;

	@Override
	public List<PostDetailsDTO> getFeed() {
		List<Post> posts = postRepository.findAll(Sort.by(Direction.DESC, Post_.CREATED_ON));
		List<PostDetailsDTO> postDetailsList = posts.stream().map(post -> postMapper.mapPostToPostDetailsDto(post))
				.collect(Collectors.toList());
		postDetailsList.forEach(post -> {
			post.setTotalLikes(postLikeRepository.countByPostId(post.getId()));
			final List<PostComment> commentsList = postCommentRepository.findByPostId(post.getId(),
					PageRequest.of(Pagination.PageNumber.DEFAULT.getValue(), Pagination.PageSize.DEFAULT.getValue(),
							Sort.by(Direction.DESC, PostComment_.COMMENTED_ON)));
			List<PostCommentDetailsDTO> comments = commentsList.stream()
					.map(comment -> postCommentMapper.mapPostCommentToPostCommentDetailsDto(comment))
					.collect(Collectors.toList());
			post.setComments(comments);
		});
		return postDetailsList;
	}

	@Override
	public List<PostDetailsDTO> getFeedByUserId(Long userId) {
		List<Post> posts = postRepository.findAll(Sort.by(Direction.DESC, Post_.CREATED_ON));
		List<PostDetailsDTO> postDetailsList = posts.stream().map(post -> postMapper.mapPostToPostDetailsDto(post))
				.collect(Collectors.toList());
		postDetailsList.forEach(post -> {
			// Updating Post with total likes
			post.setTotalLikes(postLikeRepository.countByPostId(post.getId()));
			
			// Updating Post with total comments
			post.setTotalComments(postCommentRepository.countByPostId(post.getId()));
			
			// Updating Post if post is liked by requested user
			if (postLikeService.isPostLikedByUserId(userId, post.getId())) {
				post.setLiked(true);
			} else {
				post.setLiked(false);
			}
			
			// Updating Post if post is saved by requested user
			if (postSaveService.isPostSavedByUserId(userId, post.getId())) {
				post.setSaved(true);
			} else {
				post.setSaved(false);
			}
			
			// Updating Post if post is followed by requested user
			if (post.getCreatedBy().equals(userId)) {
				post.setFollowing(true);
			} else {
				if (followService.isUserFollowedByUserId(post.getCreatedBy(), userId)) {
					post.setFollowing(true);
				} else {
					post.setFollowing(false);
				}
			}
			final List<PostComment> commentsList = postCommentRepository.findByPostId(post.getId(),
					PageRequest.of(Pagination.PageNumber.DEFAULT.getValue(), 2, //Pagination.PageSize.POST_COMMENTS.getValue()
							Sort.by(Direction.DESC, PostComment_.COMMENTED_ON)));
			List<PostCommentDetailsDTO> comments = commentsList.stream()
					.map(comment -> postCommentMapper.mapPostCommentToPostCommentDetailsDto(comment))
					.collect(Collectors.toList());
			post.setComments(comments);
		});
		return postDetailsList;
	}

}
