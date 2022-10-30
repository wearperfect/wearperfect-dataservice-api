package com.wearperfect.dataservice.api.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.dto.HashTagDTO;
import com.wearperfect.dataservice.api.entities.HashTag;
import com.wearperfect.dataservice.api.mappers.HashTagMapper;
import com.wearperfect.dataservice.api.repository.HashTagRepository;
import com.wearperfect.dataservice.api.service.HashTagService;

@Service
public class HashTagServiceImpl implements HashTagService {

	@Autowired
	HashTagRepository hashTagRepository;

	@Autowired
	HashTagMapper hashTagMapper;

	@Override
	public List<HashTagDTO> saveHashTags(Set<String> hashTags) {

		List<HashTag> existingHashTags = hashTagRepository.findByTagIn(hashTags);

		List<String> newHashTagsList = hashTags.stream()
				.filter(hashTag -> existingHashTags.stream()
						.filter(existingHashTag -> hashTag.toLowerCase().equals(existingHashTag.getTag().toLowerCase()))
						.count() <= 0)
				.collect(Collectors.toList());

		List<HashTag> newHashTags = new ArrayList<HashTag>();
		newHashTagsList.stream().forEach(hashTag -> {
			HashTag tag = new HashTag();
			tag.setActive(true);
			tag.setCreatedOn(new Date());
			tag.setTag(hashTag);
			newHashTags.add(tag);
		});

		List<HashTag> savedHashTags = new ArrayList<HashTag>();
		try {
			savedHashTags = hashTagRepository.saveAll(newHashTags);
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in saving hash tags.");
		}
		savedHashTags.addAll(existingHashTags);
		
		return savedHashTags.stream().map(hashTag -> hashTagMapper.mapHashTagToHashTagDto(hashTag))
				.collect(Collectors.toList());
	}
}
