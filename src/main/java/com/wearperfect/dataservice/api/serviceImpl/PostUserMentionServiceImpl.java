package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.dto.PostUserMentionDTO;
import com.wearperfect.dataservice.api.entity.PostUserMention;
import com.wearperfect.dataservice.api.entity.User;
import com.wearperfect.dataservice.api.mapper.PostUserMentionMapper;
import com.wearperfect.dataservice.api.repository.PostUserMentionRepository;
import com.wearperfect.dataservice.api.repository.UserRepository;
import com.wearperfect.dataservice.api.service.PostUserMentionService;

@Service
public class PostUserMentionServiceImpl implements PostUserMentionService {

	@Autowired
	PostUserMentionRepository postUserMentionRepository;

	@Autowired
	PostUserMentionMapper postUserMentionMapper;

	@Autowired
	UserRepository userRepository;

	@Override
	public List<PostUserMentionDTO> savePostUserMentions(Long postId, Set<String> usernameSet) {

		List<User> userList = userRepository.findByUsernameIn(usernameSet);

		List<PostUserMention> userMentionsList = userList.stream().filter(user -> user != null).map(user -> {
			PostUserMention postUserMention = new PostUserMention();
			postUserMention.setPostId(postId);
			postUserMention.setUserId(user.getId());
			postUserMention.setCreatedOn(new Date());
			return postUserMention;
		}).collect(Collectors.toList());

		try {
			userMentionsList = postUserMentionRepository.saveAll(userMentionsList);
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error in saving the post user mentions. " + e.getMessage());
		}

		return userMentionsList.stream()
				.map(userMention -> postUserMentionMapper.mapPostUserMentionToPostUserMentionDto(userMention))
				.collect(Collectors.toList());
	}

}
