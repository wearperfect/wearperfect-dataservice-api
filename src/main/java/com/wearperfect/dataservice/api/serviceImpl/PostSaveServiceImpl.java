package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.PostSaveDTO;
import com.wearperfect.dataservice.api.entities.PostSave;
import com.wearperfect.dataservice.api.mappers.PostSaveMapper;
import com.wearperfect.dataservice.api.repositories.PostSaveRepository;
import com.wearperfect.dataservice.api.service.PostSaveService;

@Service
@Transactional
public class PostSaveServiceImpl implements PostSaveService {

	@Autowired
	PostSaveRepository postSaveRepository;

	@Autowired
	PostSaveMapper postSaveMapper;

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
	public Long savePost(Long userId, Long postId) {

		Optional<PostSave> existingPostSave = postSaveRepository.findByPostIdAndSavedBy(postId, userId);

		if (existingPostSave.isPresent()) {
			return postId;
		}

		PostSave save = new PostSave();
		save.setPostId(postId);
		save.setSavedBy(userId);
		save.setSavedOn(new Date());
		PostSave postSave = postSaveRepository.save(save);
		return postId;
	}

	@Override
	public Long unSavePost(Long userId, Long postId) {

		Optional<PostSave> existingPostSave = postSaveRepository.findByPostIdAndSavedBy(postId, userId);

		if (existingPostSave.isPresent()) {
			postSaveRepository.deleteByPostIdAndSavedBy(postId, userId);
		}
		return postId;
	}

}
