package com.wearperfect.dataservice.api.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.dto.PostCommentDTO;
import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.dto.PostLikeDTO;
import com.wearperfect.dataservice.api.dto.PostSaveDTO;
import com.wearperfect.dataservice.api.entities.ContentType;
import com.wearperfect.dataservice.api.entities.Master;
import com.wearperfect.dataservice.api.entities.Post;
import com.wearperfect.dataservice.api.entities.PostComment;
import com.wearperfect.dataservice.api.entities.PostItem;
import com.wearperfect.dataservice.api.entities.PostLike;
import com.wearperfect.dataservice.api.entities.PostSave;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.mappers.PostCommentMapper;
import com.wearperfect.dataservice.api.mappers.PostDetailsMapper;
import com.wearperfect.dataservice.api.mappers.PostLikeMapper;
import com.wearperfect.dataservice.api.mappers.PostMapper;
import com.wearperfect.dataservice.api.mappers.PostSaveMapper;
import com.wearperfect.dataservice.api.repositories.ContentTypeRepository;
import com.wearperfect.dataservice.api.repositories.MasterRepository;
import com.wearperfect.dataservice.api.repositories.PostCommentRepository;
import com.wearperfect.dataservice.api.repositories.PostItemRepository;
import com.wearperfect.dataservice.api.repositories.PostLikeRepository;
import com.wearperfect.dataservice.api.repositories.PostRepository;
import com.wearperfect.dataservice.api.repositories.PostSaveRepository;
import com.wearperfect.dataservice.api.repositories.UserRepository;
import com.wearperfect.dataservice.api.service.PostService;
import com.wearperfect.dataservice.api.specifications.ContentTypeDetailsSpecification;
import com.wearperfect.dataservice.api.specifications.PostDetailsSpecification;
import com.wearperfect.dataservice.api.specifications.UserDetailsSpecification;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PostRepository postRepository;

	@Autowired
	PostItemRepository postItemRepository;

	@Autowired
	ContentTypeRepository contentTypeRepository;

	@Autowired
	PostLikeRepository postLikeRepository;

	@Autowired
	PostSaveRepository postSaveRepository;

	@Autowired
	PostCommentRepository postCommentRepository;

	@Autowired
	MasterRepository masterRepository;

	@Autowired
	EntityManagerFactory emf;

	@Autowired
	PostMapper postMapper;

	@Autowired
	PostDetailsMapper postDetailsmapper;

	@Autowired
	PostLikeMapper postLikeMapper;

	@Autowired
	PostSaveMapper postSaveMapper;

	@Autowired
	PostCommentMapper postCommentMapper;

	@Override
	public List<PostDetailsDTO> getPostsByUserId(Long userId) {
		List<Post> posts = postRepository.findAll(PostDetailsSpecification.postsByUserIdPredicate(userId));
		List<PostDetailsDTO> postDetailsList = posts.stream()
				.map(post -> postDetailsmapper.mapPostToPostDetailsDto(post)).collect(Collectors.toList());
		postDetailsList.forEach(post -> {
			post.setTotalLikes(postLikeRepository.countByPostId(post.getId()));
		});
		return postDetailsList;
	}

	@Override
	public PostDetailsDTO getPostByUserIdAndPostId(Long userId, Long postId) {
		Optional<Post> post = postRepository
				.findOne(PostDetailsSpecification.postByUserIdAndPostIdPredicate(userId, postId));
		PostDetailsDTO postDetails = postDetailsmapper.mapPostToPostDetailsDto(post.get());
		postDetails.setTotalLikes(postLikeRepository.countByPostId(post.get().getId()));
		return postDetails;
	}

	@Override
	public PostDetailsDTO createPost(PostDTO postDto, Long postBy, String loggedInUsername) {

		Post post = postMapper.mapPostDtoToPost(postDto);

		Optional<User> loggedInUser = userRepository
				.findOne(UserDetailsSpecification.userMobileOrEmailOrUsernamePredicate(loggedInUsername));

		if (!loggedInUser.isPresent() || loggedInUser.get().getId() != postBy) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED,
					"Requested user and loggedin user are different!");
		}

		if (null == post.getPostItems() || post.getPostItems().size() <= 0) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}

		List<PostItem> postItems = post.getPostItems();
		// TODO
		post.setPostItems(new ArrayList<>()); // Emptying post items to avoid cascading error
		post.setActive(true);
		post.setCreatedBy(postBy);
		post.setCreatedOn(new Date());
		try {
			postRepository.save(post);
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in saving the post.");
		}

		List<String> postItemExtensionsList = new LinkedList<>();
		postItems.forEach(postItem -> {
			postItemExtensionsList.add(postItem.getContentType());
		});

		int index = 0;
		postItems.forEach(postItem -> {
			postItem.setSequenceId(index + 1);
			postItem.setPostId(post.getId());
			postItem.setActive(true);
			postItem.setCreatedOn(new Date());
			Optional<ContentType> contentType = contentTypeRepository
					.findOne(ContentTypeDetailsSpecification.contentTypeExtensionPredicate(postItem.getContentType()));
			if (!contentType.isPresent()) {
				throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Content type not supported");
			}
			postItem.setContentType(contentType.get().getExtension());
		});

		try {
			postItemRepository.saveAll(postItems);
			post.setPostItems(postItems);
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in saving post items.");
		}

		return getPostByUserIdAndPostId(postBy, post.getId());
	}

	@Override
	public PostDetailsDTO createPostItems(List<PostItem> postItems, Long postId, Long userId) {
		postItems.forEach(postItem -> {
			postItem.setSequenceId(postItems.indexOf(postItem));
			postItem.setPostId(postId);
			postItem.setActive(true);
			postItem.setCreatedOn(new Date());
			Optional<ContentType> contentType = contentTypeRepository
					.findOne(ContentTypeDetailsSpecification.contentTypeExtensionPredicate(postItem.getContentType()));
			if (!contentType.isPresent()) {
				throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Content type not supported");
			}
			postItem.setContentType(contentType.get().getExtension());
		});

		try {
			postItemRepository.saveAll(postItems);
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in saving post items.");
		}

		return getPostByUserIdAndPostId(userId, postId);
	}

	@Override
	public void deletePost(Long userId, Long postId) {
		// TODO Auto-generated method stub

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

	@Override
	public PostSaveDTO savePost(Long userId, Long postId) {

		Optional<PostSave> existingPostSave = Optional
				.ofNullable(postSaveRepository.findByPostIdAndSavedBy(postId, userId));

		if (existingPostSave.isPresent()) {
			return postSaveMapper.mapPostSaveToPostSaveDto(existingPostSave.get());
		}

		PostSave save = new PostSave();
		save.setPostId(postId);
		save.setSavedBy(userId);
		save.setSavedOn(new Date());
		PostSave postSave = postSaveRepository.save(save);
		return postSaveMapper.mapPostSaveToPostSaveDto(postSave);
	}

	@Override
	public Long unSavePost(Long userId, Long postId) {

		Optional<PostSave> existingPostSave = Optional
				.ofNullable(postSaveRepository.findByPostIdAndSavedBy(postId, userId));

		if (existingPostSave.isPresent()) {
			postSaveRepository.deleteByPostIdAndSavedBy(postId, userId);
		}
		return postId;
	}

	@Override
	public List<PostCommentDTO> getComments(Long userId, Long postId) {
		List<PostComment> postComments = postCommentRepository.findAll();
		return postComments.stream().map(comment -> postCommentMapper.mapPostCommentToPostCommentDto(comment))
				.collect(Collectors.toList());
	}

	@Override
	public PostCommentDTO commentPost(Long userId, Long postId, PostCommentDTO postCommentDto) {
		PostComment postComment = postCommentMapper.mapPostCommentDtoToPostComment(postCommentDto);
		postComment.setPostId(postId);
		postComment.setCommentedBy(userId);
		postComment.setCommentedOn(new Date());
		postComment.setActive(true);
		postCommentRepository.save(postComment);
		return postCommentMapper.mapPostCommentToPostCommentDto(postComment);
	}

	@Override
	public PostCommentDTO editPostComment(Long userId, Long postId, Long commentId, PostCommentDTO postCommentDto) {
		PostComment postComment = postCommentMapper.mapPostCommentDtoToPostComment(postCommentDto);
		postComment.setLastUpdatedOn(new Date());
		postCommentRepository.save(postComment);
		return postCommentMapper.mapPostCommentToPostCommentDto(postComment);
	}

	@Override
	public Long deletePostComment(Long userId, Long postId, Long commentId) {
		Optional<PostComment> postComment = Optional.ofNullable(postCommentRepository.findByIdAndPostIdAndCommentedBy(commentId, postId, userId));
		if(postComment.isPresent()) {
			postCommentRepository.deleteById(postComment.get().getId());
		}
		return postId;
	}

	@Override
	public Master createMaster(Master master) {
		// TODO Auto-generated method stub
		master.setActive(true);
		master.setCreatedOn(new Date());
		master.getSlaves().forEach(slave -> {
			slave.setActive(true);
			slave.setCreatedOn(new Date());
			slave.setMaster(master);
		});
		return masterRepository.save(master);
	}
}
