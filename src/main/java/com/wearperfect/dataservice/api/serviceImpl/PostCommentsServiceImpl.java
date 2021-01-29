package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.PostCommentDTO;
import com.wearperfect.dataservice.api.dto.PostCommentDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserBasicDetailsDTO;
import com.wearperfect.dataservice.api.entities.PostComment;
import com.wearperfect.dataservice.api.entities.PostComment_;
import com.wearperfect.dataservice.api.mappers.PostCommentMapper;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.repositories.PostCommentRepository;
import com.wearperfect.dataservice.api.repositories.UserRepository;
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
	public List<PostCommentDetailsDTO> getComments(Long userId, Long postId) {
		List<PostComment> postComments = postCommentRepository.findByPostId(postId, PageRequest.of(0, 15, Sort.by(Direction.DESC, PostComment_.COMMENTED_ON)));
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
		UserBasicDetailsDTO userDetails = userMapper.mapUserToUserBasicDetailsDto(userRepository.findById(userId).get());
		savedCommentDto.setCommentedBy(userDetails);
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
		if(postComment.isPresent()) {
			postCommentRepository.deleteById(postComment.get().getId());
		}
		return postId;
	}
}
