package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.PostSaveDTO;
import com.wearperfect.dataservice.api.dto.PostSaveDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserPostsResponseDTO;
import com.wearperfect.dataservice.api.entity.PostSave;
import com.wearperfect.dataservice.api.mapper.PostSaveMapper;
import com.wearperfect.dataservice.api.repository.PostSaveRepository;
import com.wearperfect.dataservice.api.service.PostSaveService;
import com.wearperfect.dataservice.api.service.PostService;

@Service
@Transactional
public class PostSaveServiceImpl implements PostSaveService {

	@Autowired
	PostSaveRepository postSaveRepository;

	@Autowired
	PostSaveMapper postSaveMapper;
	
	@Autowired 
	PostService postService;

	@Override
	public List<PostSaveDTO> postSaves(Long postId) {
		List<PostSave> postSaves = postSaveRepository.findByPostId(postId);
		return postSaves.stream().map(postSave -> postSaveMapper.mapPostSaveToPostSaveDto(postSave))
				.collect(Collectors.toList());
	}

	@Override
	public Boolean isPostSavedByUserId(Long userId, Long postId) {
		Optional<PostSave> postSave = postSaveRepository.findByPostIdAndSavedBy(postId, userId);
		if (postSave.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public PostSaveDetailsDTO savePost(Long userId, Long postId) {

		Optional<PostSave> existingPostSave = postSaveRepository.findByPostIdAndSavedBy(postId, userId);

		PostSave postSave;
		
		if (existingPostSave.isPresent()) {
			postSave = existingPostSave.get();
		} else {
			PostSave save = new PostSave();
			save.setPostId(postId);
			save.setSavedBy(userId);
			save.setSavedOn(new Date());
			postSave = postSaveRepository.save(save);
		}
		
		PostSaveDetailsDTO postSaveDetails = postSaveMapper.mapPostSaveToPostSaveDetailsDto(postSave);
		
		UserPostsResponseDTO postDetails = postService.getPostByUserIdAndPostId(userId, postId);
		
		postSaveDetails.setPostDetails(postDetails.getUserPosts().get(0));
		
		return postSaveDetails;
	}

	@Override
	public PostDTO unSavePost(Long userId, Long postId) {

		Optional<PostSave> existingPostSave = postSaveRepository.findByPostIdAndSavedBy(postId, userId);

		if (existingPostSave.isPresent()) {
			postSaveRepository.deleteByPostIdAndSavedBy(postId, userId);
		}
		return postService.getPostById(postId);
	}

}
