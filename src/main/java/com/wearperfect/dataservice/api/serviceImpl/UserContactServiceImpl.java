package com.wearperfect.dataservice.api.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.UserBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserContactDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserContactSuggestionsDTO;
import com.wearperfect.dataservice.api.entities.Follow;
import com.wearperfect.dataservice.api.entities.Follow_;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.entities.UserContact;
import com.wearperfect.dataservice.api.entities.UserContact_;
import com.wearperfect.dataservice.api.mappers.UserContactMapper;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.repositories.FollowRepository;
import com.wearperfect.dataservice.api.repositories.UserContactRepository;
import com.wearperfect.dataservice.api.repositories.UserRepository;
import com.wearperfect.dataservice.api.service.UserContactService;

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
	public List<UserContactDetailsDTO> getCommunicatedContacts(Long userId) {
		List<UserContact> userContacts = userContactRepository.findByUserIdOrContactUserId(userId, userId,
				PageRequest.of(0, 100, Sort.by(Direction.DESC, UserContact_.LAST_CONTACTED_ON)));
		List<UserContactDetailsDTO> userMessagesContactsList = userContacts.stream()
				.map(userContact -> userContactMapper.mapUserContactToUserContactDetailsDto(userContact))
				.collect(Collectors.toList());
		return userMessagesContactsList;
	}

}
