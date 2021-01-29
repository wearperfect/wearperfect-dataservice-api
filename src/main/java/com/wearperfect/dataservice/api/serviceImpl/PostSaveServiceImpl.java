package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Date;
import java.util.Optional;

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
}
