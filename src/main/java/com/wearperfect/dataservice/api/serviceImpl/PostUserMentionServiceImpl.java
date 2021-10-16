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
import com.wearperfect.dataservice.api.entities.PostUserMention;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.mappers.PostUserMentionMapper;
import com.wearperfect.dataservice.api.repositories.PostUserMentionRepository;
import com.wearperfect.dataservice.api.repositories.UserRepository;
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
	public List<PostUserMentionDTO> savePostUserMentions(Long postId, Long mentionedBy, Set<String> usernameSet) {

		List<User> userList = userRepository.findByUsernameIn(usernameSet);

		List<PostUserMention> userMentionsList = userList.stream().filter(user->user!=null).map(user -> {
			PostUserMention postUserMention = new PostUserMention();
			System.out.println(postId+"|"+user.getId()+"|"+mentionedBy+"|"+new Date().getTime());
			postUserMention.setPostId(postId);
			postUserMention.setMentionedUserId(user.getId());
			postUserMention.setMentionedBy(mentionedBy);
			postUserMention.setCreatedOn(new Date());
			return postUserMention;
		}).collect(Collectors.toList());

		try {
			userMentionsList = postUserMentionRepository.saveAll(userMentionsList);
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in saving the post user mentions. "+e.getMessage());
		}

		return userMentionsList.stream()
				.map(userMention -> postUserMentionMapper.mapPostUserMentionToPostUserMentionDto(userMention))
				.collect(Collectors.toList());
	}

}
