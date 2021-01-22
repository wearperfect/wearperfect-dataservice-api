package com.wearperfect.dataservice.api.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.PostCommentDTO;
import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.entities.Post;
import com.wearperfect.dataservice.api.entities.PostComment;
import com.wearperfect.dataservice.api.entities.PostComment_;
import com.wearperfect.dataservice.api.entities.PostLike;
import com.wearperfect.dataservice.api.entities.PostSave;
import com.wearperfect.dataservice.api.mappers.PostCommentMapper;
import com.wearperfect.dataservice.api.mappers.PostDetailsMapper;
import com.wearperfect.dataservice.api.repositories.PostCommentRepository;
import com.wearperfect.dataservice.api.repositories.PostLikeRepository;
import com.wearperfect.dataservice.api.repositories.PostRepository;
import com.wearperfect.dataservice.api.repositories.PostSaveRepository;
import com.wearperfect.dataservice.api.service.FeedService;
import com.wearperfect.dataservice.api.specifications.PostDetailsSpecification;

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
	PostDetailsMapper postDetailsMapper;

	@Autowired
	PostCommentMapper postCommentMapper;

	final int POST_COMMENTS_DEFAULT_PAGE_OFFSET = 0;

	final int POST_COMMENTS_DEFAULT_PAGE_SIZE = 2;

	@Override
	public List<PostDetailsDTO> getFeed() {
		List<Post> posts = postRepository.findAll();
		List<PostDetailsDTO> postDetailsList = posts.stream()
				.map(post -> postDetailsMapper.mapPostToPostDetailsDto(post)).collect(Collectors.toList());
		postDetailsList.forEach(post -> {
			post.setTotalLikes(postLikeRepository.countByPostId(post.getId()));
			final List<PostComment> commentsList = postCommentRepository.findByPostId(post.getId(),
					PageRequest.of(POST_COMMENTS_DEFAULT_PAGE_OFFSET, POST_COMMENTS_DEFAULT_PAGE_SIZE,
							Sort.by(Direction.DESC, PostComment_.COMMENTED_ON)));
			List<PostCommentDTO> comments = commentsList.stream().map(comment->postCommentMapper.mapPostCommentToPostCommentDto(comment)).collect(Collectors.toList());
			post.setComments(comments);
		});
		return postDetailsList;
	}

	@Override
	public List<PostDetailsDTO> getFeedByUserId(Long userId) {
		List<Post> posts = postRepository.findAll(PostDetailsSpecification.postsByUserIdPredicate(userId));
		List<PostDetailsDTO> postDetailsList = posts.stream()
				.map(post -> postDetailsMapper.mapPostToPostDetailsDto(post)).collect(Collectors.toList());
		postDetailsList.forEach(post -> {
			post.setTotalLikes(postLikeRepository.countByPostId(post.getId()));
			Optional<PostLike> like = Optional
					.ofNullable(postLikeRepository.findByPostIdAndLikedBy(post.getId(), userId));
			if (like.isPresent() && like.get().getLikedBy() == userId) {
				post.setIsLiked(true);
			} else {
				post.setIsLiked(false);
			}
			Optional<PostSave> save = Optional
					.ofNullable(postSaveRepository.findByPostIdAndSavedBy(post.getId(), userId));
			if (save.isPresent() && save.get().getSavedBy() == userId) {
				post.setIsSaved(true);
			} else {
				post.setIsSaved(false);
			}
			post.setTotalComments(postCommentRepository.countByPostId(post.getId()));
			final List<PostComment> commentsList = postCommentRepository.findByPostId(post.getId(),
					PageRequest.of(POST_COMMENTS_DEFAULT_PAGE_OFFSET, POST_COMMENTS_DEFAULT_PAGE_SIZE,
							Sort.by(Direction.DESC, PostComment_.COMMENTED_ON)));
			List<PostCommentDTO> comments = commentsList.stream().map(comment->postCommentMapper.mapPostCommentToPostCommentDto(comment)).collect(Collectors.toList());
			post.setComments(comments);
		});
		return postDetailsList;
	}

}
