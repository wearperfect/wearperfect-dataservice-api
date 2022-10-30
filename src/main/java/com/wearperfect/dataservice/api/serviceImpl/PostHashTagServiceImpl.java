package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.dto.PostHashTagDTO;
import com.wearperfect.dataservice.api.entities.PostHashTag;
import com.wearperfect.dataservice.api.mappers.PostHashTagMapper;
import com.wearperfect.dataservice.api.repository.PostHashTagRepository;
import com.wearperfect.dataservice.api.service.PostHashTagService;

@Service
public class PostHashTagServiceImpl implements PostHashTagService{
	
	@Autowired
	PostHashTagRepository postHashTagRepository;
	
	@Autowired
	PostHashTagMapper postHashTagMapper;

	@Override
	public List<PostHashTagDTO> savePostHashTags(List<Long> hashTagIdList, Long postId) {

		List<PostHashTag> postHashTagsList = hashTagIdList.stream().map(hashTagId -> {
			PostHashTag postHashTag = new PostHashTag();
			postHashTag.setHashTagId(hashTagId);
			postHashTag.setPostId(postId);
			postHashTag.setActive(true);
			postHashTag.setCreatedOn(new Date());
			return postHashTag;
		}).collect(Collectors.toList());

		try {
			postHashTagsList = postHashTagRepository.saveAll(postHashTagsList);
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in saving post hash tags.");
		}
		
		return postHashTagsList.stream()
				.map(postHashTag -> postHashTagMapper.mapPostHashTagToPostHashTagDto(postHashTag))
				.collect(Collectors.toList());
	}
}
