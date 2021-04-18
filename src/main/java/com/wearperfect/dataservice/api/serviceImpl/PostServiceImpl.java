package com.wearperfect.dataservice.api.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.wearperfect.dataservice.api.constants.Pagination;
import com.wearperfect.dataservice.api.dto.PostCommentDetailsDTO;
import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserPostsResponseDTO;
import com.wearperfect.dataservice.api.entities.ContentType;
import com.wearperfect.dataservice.api.entities.Follow;
import com.wearperfect.dataservice.api.entities.Post;
import com.wearperfect.dataservice.api.entities.PostComment;
import com.wearperfect.dataservice.api.entities.PostComment_;
import com.wearperfect.dataservice.api.entities.PostItem;
import com.wearperfect.dataservice.api.entities.PostLike;
import com.wearperfect.dataservice.api.entities.PostLike_;
import com.wearperfect.dataservice.api.entities.PostSave;
import com.wearperfect.dataservice.api.entities.PostSave_;
import com.wearperfect.dataservice.api.entities.PostUserTag;
import com.wearperfect.dataservice.api.entities.PostUserTag_;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.mappers.PostCommentMapper;
import com.wearperfect.dataservice.api.mappers.PostMapper;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.repositories.ContentTypeRepository;
import com.wearperfect.dataservice.api.repositories.FollowRepository;
import com.wearperfect.dataservice.api.repositories.MasterRepository;
import com.wearperfect.dataservice.api.repositories.PostCommentRepository;
import com.wearperfect.dataservice.api.repositories.PostItemRepository;
import com.wearperfect.dataservice.api.repositories.PostLikeRepository;
import com.wearperfect.dataservice.api.repositories.PostRepository;
import com.wearperfect.dataservice.api.repositories.PostSaveRepository;
import com.wearperfect.dataservice.api.repositories.PostUserTagRepository;
import com.wearperfect.dataservice.api.repositories.UserRepository;
import com.wearperfect.dataservice.api.service.FileService;
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
	PostUserTagRepository postUserTagRepository;
	
	@Autowired
	PostCommentRepository postCommentRepository;
	
	@Autowired
	PostCommentMapper postCommentMapper;

	@Autowired
	MasterRepository masterRepository;
	
	@Autowired
	FollowRepository followRepository;

	@Autowired
	UserMapper userMapper;

	@Autowired
	PostMapper postMapper;

	@Autowired
	FileService fileService;

	@Autowired
	AmazonS3 amazonS3;

	@Value("${application.aws.s3.buckets.posts}")
	String postsS3Bucket;

	@Value("${cloud.aws.region.static}")
	private String postss3Region;

	@Override
	public UserPostsResponseDTO getPostsByUserId(Long userId) {
		List<Post> posts = postRepository.findAll(PostDetailsSpecification.postsByUserIdPredicate(userId));
		List<PostDetailsDTO> userPostsDtoList = posts.stream().map(post -> postMapper.mapPostToPostDetailsDto(post))
				.collect(Collectors.toList());
		userPostsDtoList.forEach(post -> {
			post.setTotalLikes(postLikeRepository.countByPostId(post.getId()));
		});
		return new UserPostsResponseDTO(userId, userPostsDtoList);
	}

	@Override
	public UserPostsResponseDTO getLikedPostsByUserId(Long userId) {
		List<PostLike> likedPosts = postLikeRepository.findByLikedBy(userId,
				PageRequest.of(0, 10, Sort.by(Direction.DESC, PostLike_.LIKED_ON)));
		List<Long> likedPostIds = likedPosts.stream().map(likedPost -> likedPost.getPostId())
				.collect(Collectors.toList());
		List<Post> posts = postRepository.findByIdIn(likedPostIds);
		List<PostDetailsDTO> likedPostsDtoList = posts.stream().map(post -> postMapper.mapPostToPostDetailsDto(post))
				.collect(Collectors.toList());
		return new UserPostsResponseDTO(userId, likedPostsDtoList);
	}

	@Override
	public UserPostsResponseDTO getSavedPostsByUserId(Long userId) {
		List<PostSave> savedPosts = postSaveRepository.findBySavedBy(userId,
				PageRequest.of(0, 10, Sort.by(Direction.DESC, PostSave_.SAVED_ON)));
		List<Long> savedPostsIds = savedPosts.stream().map(savedPost -> savedPost.getPostId())
				.collect(Collectors.toList());
		List<Post> posts = postRepository.findByIdIn(savedPostsIds);
		List<PostDetailsDTO> savedPostDtoList = posts.stream().map(post -> postMapper.mapPostToPostDetailsDto(post))
				.collect(Collectors.toList());
		return new UserPostsResponseDTO(userId, savedPostDtoList);
	}

	@Override
	public UserPostsResponseDTO getTaggedPostsByUserId(Long userId) {
		List<PostUserTag> taggedPosts = postUserTagRepository.findByTaggedUserId(userId,
				PageRequest.of(0, 10, Sort.by(Direction.DESC, PostUserTag_.TAGGED_ON)));
		List<Long> likedPostIds = taggedPosts.stream().map(taggedPost -> taggedPost.getPostId())
				.collect(Collectors.toList());
		List<Post> posts = postRepository.findByIdIn(likedPostIds);
		List<PostDetailsDTO> likedPostsDtoList = posts.stream().map(post -> postMapper.mapPostToPostDetailsDto(post))
				.collect(Collectors.toList());
		return new UserPostsResponseDTO(userId, likedPostsDtoList);
	}

	@Override
	public UserPostsResponseDTO getPostByUserIdAndPostId(Long userId, Long postId) {
		Optional<Post> eixistingPost = postRepository
				.findOne(PostDetailsSpecification.postByUserIdAndPostIdPredicate(userId, postId));
		PostDetailsDTO post = postMapper.mapPostToPostDetailsDto(eixistingPost.get());
		
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
		if (eixistingPost.get().getCreatedBy() == userId) {
			post.setFollowing(true);
		} else {
			Optional<Follow> follow = Optional
					.ofNullable(followRepository.findByUserIdAndFollowingBy(eixistingPost.get().getCreatedBy(), userId));
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
		
		List<PostDetailsDTO> userPostsDtoList = new ArrayList<>();
		userPostsDtoList.add(post);
		return new UserPostsResponseDTO(userId, userPostsDtoList);
	}

	@Override
	public UserPostsResponseDTO createPost(Long postBy, String loggedInUsername, PostDTO postDto,
			MultipartFile[] files) {

		Post post = postMapper.mapPostDtoToPost(postDto);

		Optional<User> loggedInUser = userRepository
				.findOne(UserDetailsSpecification.userMobileOrEmailOrUsernamePredicate(loggedInUsername));

		if (!loggedInUser.isPresent() || loggedInUser.get().getId() != postBy) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED,
					"Requested user and loggedin user are different!");
		}

		if (null == files || files.length <= 0) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "No media content available to create a post.");
		}

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

		List<PostItem> postItems = new ArrayList<>();

		for (int i = 0; i < files.length; i++) {
			PostItem postItem = new PostItem();
			postItem.setPostId(post.getId());
			postItem.setSequenceId(i + 1);
			postItem.setCreatedOn(new Date());
			postItem.setContentType(files[i].getContentType());
			postItem.setActive(true);

			File postFile = fileService.converMultipartFileToFile(files[i]);
			String fileType = fileService.getFileExtension(files[i].getOriginalFilename());
			String fileName = post.getCreatedBy() + "_" + post.getId() + "_" + (postItem.getSequenceId()) + "."
					+ fileType;
			try {
				if (!fileType.toLowerCase().equals("mp4")) {
					postItem.setAspectRatio(fileService.getFileAspectRaio(postFile));
					if (files[i].getSize() > 1000000) {
						File scaledImageFile = fileService.resizeImageByPercent(postFile, fileName, 0.50);
						amazonS3.putObject(postsS3Bucket, fileName, scaledImageFile);
						if (scaledImageFile.exists()) {
							scaledImageFile.delete();
						}
					}
				}else {
					postItem.setAspectRatio(new Float(100));
				}
				amazonS3.putObject(postsS3Bucket, fileName, postFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				postFile.delete();
			}

			postItem.setS3BucketId(1);
			postItem.setFileName(fileName);
			postItem.setSourceLink("https://" + postsS3Bucket + ".s3." + postss3Region + ".amazonaws.com/" + fileName);
			postItems.add(postItem);
		}

		try {
			postItemRepository.saveAll(postItems);
			post.setPostItems(postItems);
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in saving post items.");
		}

		return getPostByUserIdAndPostId(postBy, post.getId());
	}

	@Override
	public UserPostsResponseDTO createPostItems(List<PostItem> postItems, Long postId, Long userId) {
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
	public UserPostsResponseDTO deletePost(Long userId, Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

}
