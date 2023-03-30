package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.dto.UserBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserContactMessagesDTO;
import com.wearperfect.dataservice.api.dto.UserContactSuggestionsDTO;
import com.wearperfect.dataservice.api.entity.*;
import com.wearperfect.dataservice.api.mapper.MessageMapper;
import com.wearperfect.dataservice.api.mapper.UserContactMapper;
import com.wearperfect.dataservice.api.mapper.UserMapper;
import com.wearperfect.dataservice.api.repository.FollowRepository;
import com.wearperfect.dataservice.api.repository.MessageRepository;
import com.wearperfect.dataservice.api.repository.UserContactRepository;
import com.wearperfect.dataservice.api.repository.UserRepository;
import com.wearperfect.dataservice.api.service.MessageService;
import com.wearperfect.dataservice.api.service.UserContactService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserContactServiceImpl implements UserContactService {

	@Autowired
	UserContactRepository userContactRepository;

	@Autowired
	UserContactMapper userContactMapper;

	@Autowired
	FollowRepository followRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	MessageMapper messageMapper; 
	
	@Autowired
	MessageService messageService;

	@Override
	public UserContactSuggestionsDTO getSuggestedContacts(Long userId) {
		/* Recent */
		List<UserContact> recentUserContacts = userContactRepository.findByUserId(userId,
				PageRequest.of(0, 10, Sort.by(Direction.DESC, UserContact_.LAST_CONTACTED_ON)));
		List<Long> recentContacts = new ArrayList<Long>();
		List<UserBasicDetailsDTO> recents = new ArrayList<>();
		recentUserContacts.stream().forEach(userContact -> {
			Optional<User> user = userRepository.findById(userContact.getContactUserId());
			if (user.isPresent() && user.get().getId() != userId) {
				recentContacts.add(userContact.getContactUserId());
				recents.add(userMapper.mapUserToUserBasicDetailsDto(user.get()));
			}
		});

		/* Connections */
		List<Follow> followedAndFollowingUserContacts = followRepository
				.findByUserIdOrFollowingByAndUserIdNotInAndFollowingByNotIn(userId, userId, recentContacts,
						recentContacts, PageRequest.of(0, 25, Sort.by(Direction.DESC, Follow_.CREATED_ON)));
		List<UserBasicDetailsDTO> connections = new ArrayList<>();
		followedAndFollowingUserContacts.stream().forEach(follow -> {
			Optional<User> user = userRepository.findById(follow.getUserId());
			if (user.isPresent() && user.get().getId() != userId) {
				connections.add(userMapper.mapUserToUserBasicDetailsDto(user.get()));
			}
		});
		
		/* Searched */
		//TODO

		UserContactSuggestionsDTO suggestedContacts = new UserContactSuggestionsDTO(recents,
				new ArrayList<UserBasicDetailsDTO>(), connections);

		return suggestedContacts;
	}

	@Override
	public List<UserContactMessagesDTO> getCommunicatedContacts(Long userId) {
		List<UserContact> userContacts = userContactRepository.findByUserId(userId,
				PageRequest.of(0, 100, Sort.by(Direction.DESC, UserContact_.LAST_CONTACTED_ON)));
		List<UserContactMessagesDTO> userContactMesggesList = new ArrayList<>();
		userContacts.stream().forEach(userContact->{
			UserContactMessagesDTO userContactMessages = messageService.getUserMessagesWith(userContact.getUserId(), userContact.getContactUserId(), 0);
			userContactMesggesList.add(userContactMessages);
		});
		return userContactMesggesList;
	}

}
