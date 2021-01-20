package com.wearperfect.dataservice.api.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.entities.ContentType;
import com.wearperfect.dataservice.api.entities.Master;
import com.wearperfect.dataservice.api.entities.Post;
import com.wearperfect.dataservice.api.entities.PostItem;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.repositories.ContentTypeRepository;
import com.wearperfect.dataservice.api.repositories.MasterRepository;
import com.wearperfect.dataservice.api.repositories.PostItemRepository;
import com.wearperfect.dataservice.api.repositories.PostRepository;
import com.wearperfect.dataservice.api.repositories.UserRepository;
import com.wearperfect.dataservice.api.service.PostService;
import com.wearperfect.dataservice.api.specifications.ContentTypeDetailsSpecification;
import com.wearperfect.dataservice.api.specifications.PostDetailsSpecification;
import com.wearperfect.dataservice.api.specifications.UserDetailsSpecification;

@Service
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
	MasterRepository masterRepository;
	
	@Autowired
	EntityManagerFactory emf;

	@Override
	@Transactional
	public List<Post> getPostsByUserId(Long userId) {
		
		List<Post> posts = postRepository.findAll(PostDetailsSpecification.postsByUserIdPredicate(userId));
		posts.forEach(post -> {
			Assert.notNull(post.getCreatedByUserDetails(), "Error in fetching user created by user details");
			Assert.notNull(post.getPostItems(), "Error in fetching post items for post id: "+ post.getId());
			post.getPostItems().forEach(postItem -> {
				Assert.notNull(postItem.getContentTypeDetails(),
						"Error in fetching content type for post item id: " + postItem.getId());
			});
		});
		return posts;
	}

	@Override
	@Transactional
	public Post getPostByUserIdAndPostId(Long userId, Long postId) {
		Optional<Post> post = postRepository
				.findOne(PostDetailsSpecification.postByUserIdAndPostIdPredicate(userId, postId));
		if (post.isPresent()) {
			Assert.notNull(post.get().getCreatedByUserDetails(), "Error in fetching user created by user details");
			Assert.notNull(post.get().getPostItems(), "Error in fetching post items for post id: "+ post.get().getId());
			post.get().getPostItems().forEach(postItem -> {
				Assert.notNull(postItem.getContentTypeDetails(),
						"Error in fetching content type for post item id: " + postItem.getId());
			});
		}
		return post.get();
	}

	@Override
	public Post createPost(Post post, Long postBy, String loggedInUsername) {

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
	public Post createPostItems(List<PostItem> postItems, Long postId, Long userId) {
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
	public PostDTO likePost(Long userId, Long postId, Boolean isLiked) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDTO savePost(Long userId, Long postId, Boolean isSaved) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDTO commentPost(Long userId, Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDTO editPostComment(Long userId, Long postId, Long commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePostComment(Long userId, Long postId, Long commentId) {
		// TODO Auto-generated method stub

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
