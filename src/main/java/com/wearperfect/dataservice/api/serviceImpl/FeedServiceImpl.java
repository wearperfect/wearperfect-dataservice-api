package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.constants.Pagination;
import com.wearperfect.dataservice.api.dto.PostCommentDetailsDTO;
import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.entities.Follow;
import com.wearperfect.dataservice.api.entities.Post;
import com.wearperfect.dataservice.api.entities.PostComment;
import com.wearperfect.dataservice.api.entities.PostComment_;
import com.wearperfect.dataservice.api.entities.PostLike;
import com.wearperfect.dataservice.api.entities.PostSave;
import com.wearperfect.dataservice.api.mappers.PostCommentMapper;
import com.wearperfect.dataservice.api.mappers.PostMapper;
import com.wearperfect.dataservice.api.repositories.FollowRepository;
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
	FollowRepository followRepository;

	@Autowired
	PostMapper postMapper;

	@Autowired
	PostCommentMapper postCommentMapper;

	@Override
	public List<PostDetailsDTO> getFeed() {
		List<Post> posts = postRepository.findAll();
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
		List<Post> posts = postRepository.findAll();
		//List<Post> posts = postRepository.findAll(PostDetailsSpecification.postsByUserIdPredicate(userId));
		List<PostDetailsDTO> postDetailsList = posts.stream().map(post -> postMapper.mapPostToPostDetailsDto(post))
				.collect(Collectors.toList());
		postDetailsList.forEach(post -> {
			post.setTotalLikes(postLikeRepository.countByPostId(post.getId()));
			//
			Optional<PostLike> like = Optional
					.ofNullable(postLikeRepository.findByPostIdAndLikedBy(post.getId(), userId));
			if (like.isPresent() && like.get().getLikedBy() == userId) {
				post.setLiked(true);
			} else {
				post.setLiked(false);
			}
			//
			Optional<PostSave> save = Optional
					.ofNullable(postSaveRepository.findByPostIdAndSavedBy(post.getId(), userId));
			if (save.isPresent() && save.get().getSavedBy() == userId) {
				post.setSaved(true);
			} else {
				post.setSaved(false);
			}
			//
			if (post.getCreatedBy().getId() == userId) {
				post.setFollowing(true);
			} else {
				Optional<Follow> follow = Optional
						.ofNullable(followRepository.findByUserIdAndFollowingBy(post.getCreatedBy().getId(), userId));
				System.out.println(">>>>>>>>>>>>>>>"+follow.isPresent());
				if (follow.isPresent()) {
					post.setFollowing(true);
				} else {
					post.setFollowing(false);
				}
			}
			post.setTotalComments(postCommentRepository.countByPostId(post.getId()));
			final List<PostComment> commentsList = postCommentRepository.findByPostId(post.getId(),
					PageRequest.of(Pagination.PageNumber.DEFAULT.getValue(), Pagination.PageSize.POST_COMMENTS.getValue(),
							Sort.by(Direction.DESC, PostComment_.COMMENTED_ON)));
			List<PostCommentDetailsDTO> comments = commentsList.stream()
					.map(comment -> postCommentMapper.mapPostCommentToPostCommentDetailsDto(comment))
					.collect(Collectors.toList());
			post.setComments(comments);
		});
		return postDetailsList;
	}

}
