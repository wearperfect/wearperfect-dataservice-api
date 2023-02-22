package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.wearperfect.dataservice.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.constant.Pagination;
import com.wearperfect.dataservice.api.dto.PostCommentDTO;
import com.wearperfect.dataservice.api.dto.PostCommentDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserBasicDetailsDTO;
import com.wearperfect.dataservice.api.entity.PostComment;
import com.wearperfect.dataservice.api.entity.PostComment_;
import com.wearperfect.dataservice.api.mapper.PostCommentMapper;
import com.wearperfect.dataservice.api.mapper.UserMapper;
import com.wearperfect.dataservice.api.repository.PostCommentRepository;
import com.wearperfect.dataservice.api.repository.UserRepository;
import com.wearperfect.dataservice.api.service.PostCommentService;

@Service
@Transactional
public class PostCommentsServiceImpl implements PostCommentService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostCommentRepository postCommentRepository;
	
	@Autowired
	UserMapper userMapper;

	@Autowired
	PostCommentMapper postCommentMapper;

	@Override
	public List<PostCommentDetailsDTO> getComments(Long userId, Long postId, Integer page) {
		if(null == page || page < 0 ) {
			page = Pagination.PageNumber.DEFAULT.getValue();
		}
		List<PostComment> postComments = postCommentRepository.findByPostId(postId, PageRequest.of(page, Pagination.PageSize.POST_COMMENTS.getValue(), Sort.by(Direction.DESC, PostComment_.COMMENTED_ON)));
		return postComments.stream().map(comment -> postCommentMapper.mapPostCommentToPostCommentDetailsDto(comment))
				.collect(Collectors.toList());
	}

	@Override
	public PostCommentDetailsDTO commentPost(Long userId, Long postId, PostCommentDTO postCommentDto) {
		PostComment postComment = postCommentMapper.mapPostCommentDtoToPostComment(postCommentDto);
		postComment.setPostId(postId);
		postComment.setCommentedBy(userId);
		postComment.setCommentedOn(new Date());
		postComment.setActive(true);
		postCommentRepository.save(postComment);
		PostCommentDetailsDTO savedCommentDto = postCommentMapper.mapPostCommentToPostCommentDetailsDto(postComment);
		Optional<User> user = userRepository.findById(userId);
		user.ifPresent(value -> {
			UserBasicDetailsDTO userDetails = userMapper.mapUserToUserBasicDetailsDto(userRepository.findById(userId).get());
			savedCommentDto.setCommentedBy(userDetails);
		});
		return savedCommentDto;
	}

	@Override
	public PostCommentDetailsDTO editPostComment(Long userId, Long postId, Long commentId, PostCommentDTO postCommentDto) {
		PostComment postComment = postCommentMapper.mapPostCommentDtoToPostComment(postCommentDto);
		postComment.setLastUpdatedOn(new Date());
		postCommentRepository.save(postComment);
		return postCommentMapper.mapPostCommentToPostCommentDetailsDto(postComment);
	}

	@Override
	public Long deletePostComment(Long userId, Long postId, Long commentId) {
		Optional<PostComment> postComment = Optional.ofNullable(postCommentRepository.findByIdAndPostIdAndCommentedBy(commentId, postId, userId));
		postComment.ifPresent(comment -> postCommentRepository.deleteById(comment.getId()));
		return postId;
	}
}
