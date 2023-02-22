package com.wearperfect.dataservice.api.serviceImpl;

import com.amazonaws.services.s3.AmazonS3;
import com.wearperfect.dataservice.api.constant.Pagination;
import com.wearperfect.dataservice.api.dto.*;
import com.wearperfect.dataservice.api.entity.*;
import com.wearperfect.dataservice.api.mapper.*;
import com.wearperfect.dataservice.api.repository.*;
import com.wearperfect.dataservice.api.security.models.WearperfectUserPrincipal;
import com.wearperfect.dataservice.api.security.service.WearperfectUserDetailsService;
import com.wearperfect.dataservice.api.service.*;
import com.wearperfect.dataservice.api.specification.PostDetailsSpecification;
import com.wearperfect.dataservice.api.specification.UserDetailsSpecification;
import com.wearperfect.dataservice.api.utility.service.TextUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	WearperfectUserDetailsService wearperfectUserDetailsService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PostRepository postRepository;

	@Autowired
	PostMediaRepository postMediaRepository;

	@Autowired
	PostMediaMapper postMediaMapper;

	@Autowired
	PostMediaUserTagMapper postMediaUserTagMapper;

	@Autowired
	ContentTypeRepository contentTypeRepository;

	@Autowired
	PostLikeRepository postLikeRepository;

	@Autowired
	PostSaveRepository postSaveRepository;

	@Autowired
	PostMediaUserTagRepository postMediaUserTagRepository;

	@Autowired
	PostCommentRepository postCommentRepository;

	@Autowired
	PostCommentMapper postCommentMapper;

	@Autowired
	MasterRepository masterRepository;

	@Autowired
	FollowRepository followRepository;

	@Autowired
	UserService userService;

	@Autowired
	UserMapper userMapper;

	@Autowired
	PostMapper postMapper;

	@Autowired
	FileService fileService;

	@Autowired
	HashTagService hashTagService;

	@Autowired
	PostHashTagService postHashTagService;

	@Autowired
	PostUserMentionService postUserMentionService;

	@Autowired
	TextUtilService textUtilService;

	@Autowired
	AmazonS3 amazonS3;

	@Value("${application.aws.s3.buckets.posts}")
	String postsS3Bucket;

	@Value("${cloud.aws.region.static}")
	private String postss3Region;

	@Override
	public PostDTO getPostById(Long postId) {
		Optional<Post> post = postRepository.findById(postId);
		if (post.isPresent()) {
			return postMapper.mapPostToPostDto(post.get());
		} else {
			throw new EntityNotFoundException("Post not found with postId: " + postId);
		}
	}

	@Override
	public UserPostsResponseDTO getPostsByUserId(Long userId) {
		List<Post> posts = postRepository.findByCreatedBy(userId,
				PageRequest.of(0, Pagination.PageSize.POSTS.getValue(), Sort.by(Direction.DESC, Post_.CREATED_ON)));
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
		List<Post> posts = postRepository.findByIdIn(likedPostIds,
				PageRequest.of(0, 10, Sort.by(Direction.DESC, Post_.CREATED_ON)));
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
		List<Post> posts = postRepository.findByIdIn(savedPostsIds,
				PageRequest.of(0, 10, Sort.by(Direction.DESC, Post_.CREATED_ON)));
		List<PostDetailsDTO> savedPostDtoList = posts.stream().map(post -> postMapper.mapPostToPostDetailsDto(post))
				.collect(Collectors.toList());
		return new UserPostsResponseDTO(userId, savedPostDtoList);
	}

	@Override
	public UserPostsResponseDTO getTaggedPostsByUserId(Long userId) {
		List<PostMediaUserTag> taggedPosts = postMediaUserTagRepository.findByTaggedUserId(userId,
				PageRequest.of(0, 10, Sort.by(Direction.DESC, PostMediaUserTag_.CREATED_ON)));
		List<Long> likedPostIds = taggedPosts.stream().map(taggedPost -> taggedPost.getPostMediaDetails().getPostId())
				.collect(Collectors.toList());
		List<Post> posts = postRepository.findByIdIn(likedPostIds,
				PageRequest.of(0, 10, Sort.by(Direction.DESC, Post_.CREATED_ON)));
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
		Optional<PostLike> like = Optional.ofNullable(postLikeRepository.findByPostIdAndLikedBy(post.getId(), userId));
		if (like.isPresent() && like.get().getLikedBy() == userId) {
			post.setLiked(true);
		} else {
			post.setLiked(false);
		}
		//
		Optional<PostSave> save = postSaveRepository.findByPostIdAndSavedBy(post.getId(), userId);
		if (save.isPresent() && save.get().getSavedBy() == userId) {
			post.setSaved(true);
		} else {
			post.setSaved(false);
		}
		//
		if (eixistingPost.get().getCreatedBy() == userId) {
			post.setFollowing(true);
		} else {
			Optional<Follow> follow = followRepository.findByUserIdAndFollowingBy(eixistingPost.get().getCreatedBy(),
					userId);
			System.out.println(">>>>>>>>>>>>>>>" + follow.isPresent());
			if (follow.isPresent()) {
				post.setFollowing(true);
			} else {
				post.setFollowing(false);
			}
		}

		// Set total comments in Post
		post.setTotalComments(postCommentRepository.countByPostId(post.getId()));

		// Set comments in Post
		final List<PostComment> commentsList = postCommentRepository.findByPostId(post.getId(),
				PageRequest.of(Pagination.PageNumber.DEFAULT.getValue(), Pagination.PageSize.POST_COMMENTS.getValue(),
						Sort.by(Direction.DESC, PostComment_.COMMENTED_ON)));
		List<PostCommentDetailsDTO> comments = commentsList.stream()
				.map(comment -> postCommentMapper.mapPostCommentToPostCommentDetailsDto(comment))
				.collect(Collectors.toList());
		post.setComments(comments);

		List<PostDetailsDTO> userPostsDtoList = new ArrayList<>();
		userPostsDtoList.add(post);

		post.setCreatedByUserDetails(userMapper.mapUserToUserBasicDetailsDto(userRepository.findById(userId).get()));
		return new UserPostsResponseDTO(userId, userPostsDtoList);
	}

	@Override
	public UserPostsResponseDTO createPost(Long postBy, String loggedInUsername, PostDetailsDTO postDetailsDto,
			MultipartFile[] files) {

		Optional<User> loggedInUser = userRepository
				.findOne(UserDetailsSpecification.userMobileOrEmailOrUsernamePredicate(loggedInUsername));

		if (!loggedInUser.isPresent() || loggedInUser.get().getId() != postBy) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED,
					"Requested user and loggedin user are different!");
		}

		if (null == files || files.length <= 0) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "No media content available to create a post.");
		}

		Post post = new Post();
		post.setDescription(postDetailsDto.getDescription());
		post.setPostMediaList(new ArrayList<>()); // Emptying post items to avoid cascading error
		post.setActive(true);
		post.setCreatedBy(postBy);
		post.setCreatedOn(new Date());

		try {
			post = postRepository.save(post);
			postDetailsDto.setId(post.getId());
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in saving the post.");
		}

		// Save Post Media
		try {
			List<PostMedia> postMediaList = savePostMedia(postDetailsDto, files);
			post.setPostMediaList(postMediaList);
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error in creating post because of error in saving post media. " + e.getMessage());
		}

		// Save tagged Hash Tags
		try {
			savePostHashTags(post.getId(), post.getDescription());
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error in creating post because of error in saving post hash tags. " + e.getMessage());
		}

		// Save mentioned User Mentions
		try {
			savePostUserMentions(post.getId(), post.getDescription());
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error in creating post because of error in saving user mentions. " + e.getMessage());
		}

		return getPostByUserIdAndPostId(postBy, post.getId());
	}

	@Override
	public PostDTO deletePost(Long userId, Long postId) {
		WearperfectUserPrincipal loggedInUserDetails = wearperfectUserDetailsService.getLoggedInUserDetails();
		if (!loggedInUserDetails.getUserId().equals(userId)) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "User cannot delete other's posts.");
		}

		Optional<Post> post = postRepository.findByIdAndCreatedBy(postId, userId);
		if (!post.isPresent()) {
			List<PostMedia> postMediaList = post.get().getPostMediaList();
			postRepository.deleteById(post.get().getId());
			postMediaList.stream().forEach(postMedia -> {
				System.out.println(postMedia.getSourceLink());
				amazonS3.deleteObject(postsS3Bucket, postMedia.getFileName());
			});
			return postMapper.mapPostToPostDto(post.get());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Post not found.");
		}
	}

	// ############PRIVATE METHODS############
	private List<PostMedia> savePostMedia(PostDetailsDTO postDetailsDto, MultipartFile[] files) {

		List<PostMedia> postMediaList = new ArrayList<>();

		for (int i = 0; i < files.length; i++) {
			PostMedia postMedia = postMediaMapper
					.mapPostMediaDetailsDtoToPostMedia(postDetailsDto.getPostMediaList().get(i));
			postMedia.setPostId(postDetailsDto.getId());
			postMedia.setSequenceId(i + 1);
			postMedia.setCreatedOn(new Date());
			postMedia.setContentType(files[i].getContentType());
			postMedia.setActive(true);

			File postFile = fileService.converMultipartFileToFile(files[i]);
			String fileType = fileService.getFileExtension(files[i].getOriginalFilename());
			String fileName = postDetailsDto.getCreatedBy() + "_" + postDetailsDto.getId() + "_"
					+ (postMedia.getSequenceId()) + "." + fileType;
			System.out.println(i + " Type:::" + files[i].getOriginalFilename());
			try {
				if (!fileType.toLowerCase().equals("mp4")) {
					postMedia.setAspectRatio(fileService.getFileAspectRaio(postFile));
					postMedia.setHeight(fileService.getFileHeight(postFile));
					postMedia.setWidth(fileService.getFileWidth(postFile));
					if (files[i].getSize() > 1000000) {
						File scaledImageFile = fileService.resizeImageByPercent(postFile, fileName, 0.50);
						amazonS3.putObject(postsS3Bucket, fileName, scaledImageFile);
						if (scaledImageFile.exists()) {
							scaledImageFile.delete();
						}
					}
				} else {
					postMedia.setAspectRatio(100f);
				}
				amazonS3.putObject(postsS3Bucket, fileName, postFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				postFile.delete();
			}

			postMedia.setS3BucketId(1);
			postMedia.setFileName(fileName);
			postMedia.setSourceLink("https://" + postsS3Bucket + ".s3." + postss3Region + ".amazonaws.com/" + fileName);

			try {
				postMedia = postMediaRepository.save(postMedia);
				postMediaList.add(postMedia);
			} catch (Exception e) {
				throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Error in creating post because of error in saving post media.");
			}

			// Save Post Media User Tags
			List<PostMediaUserTag> userTags = postMedia.getUserTags();
			try {
				userTags = savePostMediaUserTags(postMedia, userTags);
				postMedia.setUserTags(userTags);
			} catch (Exception e) {
				throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Error in creating post because of error in saving post media user tags. " + e.getMessage());
			}
		}

		return postMediaList;
	}

	private List<PostMediaUserTag> savePostMediaUserTags(PostMedia postMedia,
			List<PostMediaUserTag> postMediaUserTags) {

		for (int j = 0; j < postMediaUserTags.size(); j++) {
			postMediaUserTags.get(j).setCreatedOn(postMedia.getCreatedOn());
			postMediaUserTags.get(j).setPostMediaId(postMedia.getId());
			if (null == postMediaUserTags.get(j).getTagLocationX()) {
				postMediaUserTags.get(j).setTagLocationX(0d);
			}
			if (null == postMediaUserTags.get(j).getTagLocationY()) {
				postMediaUserTags.get(j).setTagLocationY(0d);
			}
		}

		try {
			postMediaUserTags = postMediaUserTagRepository.saveAll(postMediaUserTags);
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error in saving post media. " + e.getMessage());
		}

		return postMediaUserTags;
	}

	private List<PostHashTagDTO> savePostHashTags(Long postId, String postCaption) {

		Set<String> hashTags = textUtilService.extractHashTagsFromText(postCaption);

		// Save new hash tags to HashTags table
		List<HashTagDTO> savedHashTagDtoList = hashTagService.saveHashTags(hashTags);

		List<Long> savedHashTagIdList = savedHashTagDtoList.stream().map(hashTagDto -> hashTagDto.getId())
				.collect(Collectors.toList());

		// Save tagged hash tags to PostHashTags table
		List<PostHashTagDTO> postHashTagDtoList = postHashTagService.savePostHashTags(savedHashTagIdList, postId);

		return postHashTagDtoList;
	}

	private List<PostUserMentionDTO> savePostUserMentions(Long postId, String postCaption) {

		Set<String> userMentionSet = textUtilService.extractUserMentionsFromText(postCaption);

		List<PostUserMentionDTO> postUserMentionsList = postUserMentionService.savePostUserMentions(postId,
				userMentionSet);

		return postUserMentionsList;
	}

}
