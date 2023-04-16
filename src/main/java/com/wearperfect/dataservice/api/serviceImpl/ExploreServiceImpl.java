package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.constant.Pagination;
import com.wearperfect.dataservice.api.dto.PostCommentDetailsDTO;
import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserProfileDTO;
import com.wearperfect.dataservice.api.entity.Post;
import com.wearperfect.dataservice.api.entity.PostComment;
import com.wearperfect.dataservice.api.entity.PostComment_;
import com.wearperfect.dataservice.api.entity.User;
import com.wearperfect.dataservice.api.mapper.PostCommentMapper;
import com.wearperfect.dataservice.api.mapper.PostMapper;
import com.wearperfect.dataservice.api.mapper.UserMapper;
import com.wearperfect.dataservice.api.repository.PostCommentRepository;
import com.wearperfect.dataservice.api.repository.PostLikeRepository;
import com.wearperfect.dataservice.api.repository.PostRepository;
import com.wearperfect.dataservice.api.repository.UserRepository;
import com.wearperfect.dataservice.api.service.ExploreService;

@Service
@Transactional
public class ExploreServiceImpl implements ExploreService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	PostMapper postMapper;
	
	@Autowired
	PostLikeRepository postLikeRepository;
	
	@Autowired
	PostCommentRepository postCommentRepository;
	
	@Autowired
	PostCommentMapper postCommentMapper;

	@Override
	public List<PostDetailsDTO> explorePosts() {
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
	public List<UserProfileDTO> exploreDesigners() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user->userMapper.mapUserToUserDetailsDto(user)).collect(Collectors.toList());
	}

	@Override
	public List<UserProfileDTO> exploreBrands() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user->userMapper.mapUserToUserDetailsDto(user)).collect(Collectors.toList());
	}

}
