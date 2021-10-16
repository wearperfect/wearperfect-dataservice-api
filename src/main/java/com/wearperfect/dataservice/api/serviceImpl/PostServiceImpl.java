package com.wearperfect.dataservice.api.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.wearperfect.dataservice.api.constants.Pagination;
import com.wearperfect.dataservice.api.dto.HashTagDTO;
import com.wearperfect.dataservice.api.dto.PostCommentDetailsDTO;
import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserPostsResponseDTO;
import com.wearperfect.dataservice.api.entities.ContentType;
import com.wearperfect.dataservice.api.entities.Follow;
import com.wearperfect.dataservice.api.entities.Post;
import com.wearperfect.dataservice.api.entities.PostComment;
import com.wearperfect.dataservice.api.entities.PostComment_;
import com.wearperfect.dataservice.api.entities.PostLike;
import com.wearperfect.dataservice.api.entities.PostLike_;
import com.wearperfect.dataservice.api.entities.PostMedia;
import com.wearperfect.dataservice.api.entities.PostMediaUserTag;
import com.wearperfect.dataservice.api.entities.PostMediaUserTag_;
import com.wearperfect.dataservice.api.entities.PostSave;
import com.wearperfect.dataservice.api.entities.PostSave_;
import com.wearperfect.dataservice.api.entities.Post_;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.mappers.PostCommentMapper;
import com.wearperfect.dataservice.api.mappers.PostMapper;
import com.wearperfect.dataservice.api.mappers.PostMediaMapper;
import com.wearperfect.dataservice.api.mappers.PostMediaUserTagMapper;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.repositories.ContentTypeRepository;
import com.wearperfect.dataservice.api.repositories.FollowRepository;
import com.wearperfect.dataservice.api.repositories.MasterRepository;
import com.wearperfect.dataservice.api.repositories.PostCommentRepository;
import com.wearperfect.dataservice.api.repositories.PostLikeRepository;
import com.wearperfect.dataservice.api.repositories.PostMediaRepository;
import com.wearperfect.dataservice.api.repositories.PostMediaUserTagRepository;
import com.wearperfect.dataservice.api.repositories.PostRepository;
import com.wearperfect.dataservice.api.repositories.PostSaveRepository;
import com.wearperfect.dataservice.api.repositories.UserRepository;
import com.wearperfect.dataservice.api.security.models.WearperfectUserDetails;
import com.wearperfect.dataservice.api.security.service.WearperfectUserDetailsService;
import com.wearperfect.dataservice.api.service.FileService;
import com.wearperfect.dataservice.api.service.HashTagService;
import com.wearperfect.dataservice.api.service.PostService;
import com.wearperfect.dataservice.api.service.PostUserMentionService;
import com.wearperfect.dataservice.api.specifications.ContentTypeDetailsSpecification;
import com.wearperfect.dataservice.api.specifications.PostDetailsSpecification;
import com.wearperfect.dataservice.api.specifications.UserDetailsSpecification;

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
	UserMapper userMapper;

	@Autowired
	PostMapper postMapper;

	@Autowired
	FileService fileService;

	@Autowired
	HashTagService hashTagService;
	
	@Autowired
	PostUserMentionService postUserMentionService;

	@Autowired
	AmazonS3 amazonS3;

	@Value("${application.aws.s3.buckets.posts}")
	String postsS3Bucket;

	@Value("${cloud.aws.region.static}")
	private String postss3Region;

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
		Optional<PostSave> save = Optional.ofNullable(postSaveRepository.findByPostIdAndSavedBy(post.getId(), userId));
		if (save.isPresent() && save.get().getSavedBy() == userId) {
			post.setSaved(true);
		} else {
			post.setSaved(false);
		}
		//
		if (eixistingPost.get().getCreatedBy() == userId) {
			post.setFollowing(true);
		} else {
			Optional<Follow> follow = Optional.ofNullable(
					followRepository.findByUserIdAndFollowingBy(eixistingPost.get().getCreatedBy(), userId));
			System.out.println(">>>>>>>>>>>>>>>" + follow.isPresent());
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

		Date creationDate = new Date();

		Post post = new Post();
		post.setDescription(postDetailsDto.getDescription());
		post.setPostMediaList(new ArrayList<>()); // Emptying post items to avoid cascading error
		post.setActive(true);
		post.setCreatedBy(postBy);
		post.setCreatedOn(creationDate);

		try {
			postRepository.save(post);
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in saving the post.");
		}

		List<PostMedia> postMediaList = new ArrayList<>();

		for (int i = 0; i < files.length; i++) {
			PostMedia postMedia = postMediaMapper
					.mapPostMediaDetailsDtoToPostMedia(postDetailsDto.getPostMediaList().get(i));
			postMedia.setPostId(post.getId());
			postMedia.setSequenceId(i + 1);
			postMedia.setCreatedOn(post.getCreatedOn());
			postMedia.setContentType(files[i].getContentType());
			postMedia.setActive(true);

			File postFile = fileService.converMultipartFileToFile(files[i]);
			String fileType = fileService.getFileExtension(files[i].getOriginalFilename());
			String fileName = post.getCreatedBy() + "_" + post.getId() + "_" + (postMedia.getSequenceId()) + "."
					+ fileType;
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
				throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in creating post because of error in saving post media.");
			}

			List<PostMediaUserTag> userTags = postMedia.getUserTags();
			for (int j = 0; j < userTags.size(); j++) {
				userTags.get(j).setCreatedOn(postMedia.getCreatedOn());
				userTags.get(j).setPostMediaId(postMedia.getId());
				if (null == userTags.get(j).getTagLocationX()) {
					userTags.get(j).setTagLocationX(0d);
				}
				if (null == userTags.get(j).getTagLocationY()) {
					userTags.get(j).setTagLocationY(0d);
				}
			}
			try {
				userTags = postMediaUserTagRepository.saveAll(userTags);
				postMedia.setUserTags(userTags);
			} catch (Exception e) {
				throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Error in creating post because of error in saving post media user tags. " + e.getMessage());
			}
		}
		
		post.setPostMediaList(postMediaList);

		// Hash Tags
		String hashTagRegexPattern = "(#\\w+)";
		Pattern hashTagPattern = Pattern.compile(hashTagRegexPattern);
		Matcher hashTagMatcher = hashTagPattern.matcher(post.getDescription());
		LinkedHashSet<String> hashTags = new LinkedHashSet<String>();
		while (hashTagMatcher.find()) {
			hashTags.add(hashTagMatcher.group(1).substring(1));
		}

		List<HashTagDTO> savedHashTagDtoList = new ArrayList<>();
		
		try {
			savedHashTagDtoList  = hashTagService.saveHashTags(hashTags);
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error in creating post because of error in new post hash tags. " + e.getMessage());
		}
		
		List<Long> savedHashTagIdList = savedHashTagDtoList.stream().map(hashTagDto -> hashTagDto.getId())
				.collect(Collectors.toList());
		
		try {
			hashTagService.savePostHashTags(savedHashTagIdList, post.getId());
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error in creating post because of error in saving post hash tags. " + e.getMessage());
		}
		
		// User Mentions
		String userMentionRegexPattern = "(@\\w+)";
		Pattern userMentionPattern = Pattern.compile(userMentionRegexPattern);
		Matcher userMentionMatcher = userMentionPattern.matcher(post.getDescription());
		LinkedHashSet<String> userMentionSet = new LinkedHashSet<String>();
		while (userMentionMatcher.find()) {
			userMentionSet.add(userMentionMatcher.group(1).substring(1));
		}
		System.out.println(userMentionSet);
		try {
			postUserMentionService.savePostUserMentions(post.getId(), postBy, userMentionSet);
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
			"Error in creating post because of error in saving post user mentions." + e.getMessage());
		}

		return getPostByUserIdAndPostId(postBy, post.getId());
	}

	@Override
	public UserPostsResponseDTO savePostMediaList(List<PostMedia> postMediaList, Long postId, Long userId) {
		postMediaList.forEach(postItem -> {
			postItem.setSequenceId(postMediaList.indexOf(postItem));
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
			postMediaRepository.saveAll(postMediaList);
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in saving post items.");
		}

		return getPostByUserIdAndPostId(userId, postId);
	}

	@Override
	public PostDTO deletePost(Long userId, Long postId) {
		WearperfectUserDetails loggedInUserDetails = wearperfectUserDetailsService.getLoggedInUserDetails();
		if (!loggedInUserDetails.getUserId().equals(userId)) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "User cannot delete other's posts.");
		}

		Optional<Post> post = postRepository.findByIdAndCreatedBy(postId, userId);
		if (!post.isEmpty()) {
			List<PostMedia> postMediaList = post.get().getPostMediaList();
			postRepository.deleteById(post.get().getId());
			postMediaList.stream().forEach(postMedia->{
				System.out.println(postMedia.getSourceLink());
				amazonS3.deleteObject(postsS3Bucket, postMedia.getFileName());
			});
			return postMapper.mapPostToPostDto(post.get());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Post not found.");
		}
	}

}
