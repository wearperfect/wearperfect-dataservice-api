package com.wearperfect.dataservice.api.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.dto.MessageDTO;
import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.UserBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserContactMessageDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserContactSuggestionsDTO;
import com.wearperfect.dataservice.api.dto.UserPostsResponseDTO;
import com.wearperfect.dataservice.api.entities.Follow;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.entities.UserContact;
import com.wearperfect.dataservice.api.entities.UserContact_;
import com.wearperfect.dataservice.api.mappers.MessageMapper;
import com.wearperfect.dataservice.api.mappers.UserContactMapper;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.repositories.FollowRepository;
import com.wearperfect.dataservice.api.repositories.MessageRepository;
import com.wearperfect.dataservice.api.repositories.UserContactRepository;
import com.wearperfect.dataservice.api.repositories.UserRepository;
import com.wearperfect.dataservice.api.service.MessageService;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	MessageMapper messageMapper;

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
	public UserContactSuggestionsDTO getUserContactSuggestionsByUserId(Long userId) {
		List<UserContact> recentUserContacts = userContactRepository.findByUserId(userId,
				PageRequest.of(0, 100, Sort.by(Direction.DESC, UserContact_.LAST_CONTACTED_ON)));
		List<Long> recentContacts = new ArrayList<Long>();
		List<UserBasicDetailsDTO> recentlyContacted = new ArrayList<>();
		recentUserContacts.stream().forEach(userContact -> {
			Optional<User> user = userRepository.findById(userContact.getContactUserId());
			if (user.isPresent() && user.get().getId() != userId) {
				recentContacts.add(userContact.getContactUserId());
				recentlyContacted.add(userMapper.mapUserToUserBasicDetailsDto(user.get()));
			}
		});

		
		List<Follow> followedAndFollowingUserContacts = followRepository
				.findByUserIdOrFollowingBy(userId, userId);
		Set<Long> followContacts = new HashSet<>();
		followedAndFollowingUserContacts.stream().forEach(follow -> {
			followContacts.add(follow.getUserId());
		});
		List<UserBasicDetailsDTO> others = new ArrayList<>();
		followContacts.stream().forEach(followUserId -> {
			Optional<User> user = userRepository.findById(followUserId);
			if (user.isPresent() && user.get().getId() != userId) {
				others.add(userMapper.mapUserToUserBasicDetailsDto(user.get()));
			}
		});

		UserContactSuggestionsDTO suggestedContacts = new UserContactSuggestionsDTO(recentlyContacted, others);

		return suggestedContacts;
	}

	@Override
	public List<UserContactMessageDetailsDTO> getCommunicatedUserContactsByUserId(Long userId) {
		List<UserContact> userContacts = userContactRepository.findByUserId(userId,
				PageRequest.of(0, 100, Sort.by(Direction.DESC, UserContact_.LAST_CONTACTED_ON)));
		List<UserContactMessageDetailsDTO> userMessagesContactsList = userContacts.stream()
				.map(userContact -> userContactMapper.mapUserContactToUserContactMessageDetailsDto(userContact))
				.collect(Collectors.toList());
		return userMessagesContactsList;
	}

	@Override
	public UserPostsResponseDTO getUserMessagesWith(Long sentBy, Long sentTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserPostsResponseDTO sendMessage(Long sentBy, String name, MessageDTO messageDto, MultipartFile[] files) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDTO deleteMessage(Long sentBy, Long messageId) {
		// TODO Auto-generated method stub
		return null;
	}

}
