package com.wearperfect.dataservice.api.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.dto.MessageDTO;
import com.wearperfect.dataservice.api.dto.MessageDetailsDTO;
import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.UserBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserContactDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserContactMessageDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserContactSuggestionsDTO;
import com.wearperfect.dataservice.api.dto.UserPostsResponseDTO;
import com.wearperfect.dataservice.api.entities.Follow;
import com.wearperfect.dataservice.api.entities.Follow_;
import com.wearperfect.dataservice.api.entities.Message;
import com.wearperfect.dataservice.api.entities.Message_;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.entities.UserContact;
import com.wearperfect.dataservice.api.entities.UserContact_;
import com.wearperfect.dataservice.api.exceptions.BadRequestException;
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
				PageRequest.of(0, 10, Sort.by(Direction.DESC, UserContact_.LAST_CONTACTED_ON)));
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
				.findByUserIdOrFollowingByAndUserIdNotInAndFollowingByNotIn(userId, userId, recentContacts,
						recentContacts, PageRequest.of(0, 25, Sort.by(Direction.DESC, Follow_.CREATED_ON)));
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
		List<UserContact> userContacts = userContactRepository.findByUserIdOrContactUserId(userId, userId,
				PageRequest.of(0, 100, Sort.by(Direction.DESC, UserContact_.LAST_CONTACTED_ON)));
		List<UserContactMessageDetailsDTO> userMessagesContactsList = userContacts.stream()
				.map(userContact -> userContactMapper.mapUserContactToUserContactMessageDetailsDto(userContact))
				.collect(Collectors.toList());
		return userMessagesContactsList;
	}

	@Override
	public UserContactMessageDetailsDTO getCommunicatedUserContactByUserIdAndContactUserId(Long userId,
			Long contactUserId) {
		Optional<UserContact> userContact = userContactRepository.findByUserIdAndContactUserId(userId, contactUserId);
		if (userContact.isPresent()) {
			return userContactMapper.mapUserContactToUserContactMessageDetailsDto(userContact.get());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Contact not found.");
		}
	}

	@Override
	public UserContactMessageDetailsDTO getUserMessagesWith(Long userId, Long targetUserId) {
		Long[] sentBySentToList = {userId, targetUserId};
		List<Message> messages = messageRepository.findBySentByInAndSentToIn( sentBySentToList, sentBySentToList,
				PageRequest.of(0, 50, Sort.by(Direction.ASC, Message_.CREATED_ON)));
		Optional<UserContact> userContact = userContactRepository.findByUserIdInAndContactUserIdIn(sentBySentToList, sentBySentToList);
		UserContactMessageDetailsDTO userContactMessageDetails = userContactMapper.mapUserContactToUserContactMessageDetailsDto(userContact.get());
		List<MessageDetailsDTO> userMessages = messages.stream().map(message->messageMapper.mapMessageToMessageDetailsDto(message)).collect(Collectors.toList());
		userContactMessageDetails.setMessages(userMessages);
		return userContactMessageDetails;
	}

	@Override
	public UserContactMessageDetailsDTO sendMessage(Long sentBy, String name, MessageDTO messageDto,
			MultipartFile[] files) {
		Message message = messageMapper.mapMessageDtoToMessage(messageDto);
		if (null == message.getSentBy() || null == message.getSentTo()) {
			throw new BadRequestException("sentBy and sentTo fields should not null.");
		}

		Date currentTimestamp = new Date();
		
		message.setCreatedOn(currentTimestamp);
		messageRepository.save(message);

		Long[] userIdList = {message.getSentBy(), message.getSentTo()};
		Optional<UserContact> userContact = userContactRepository.findByUserIdInAndContactUserIdIn(userIdList, userIdList);
		if (userContact.isPresent()) {
			userContact.get().setLastContactedOn(new Date());
			return userContactMapper.mapUserContactToUserContactMessageDetailsDto(userContact.get());
		} else {
			UserContact newUserContact = new UserContact();
			newUserContact.setUserId(messageDto.getSentBy());
			newUserContact.setContactUserId(messageDto.getSentTo());
			newUserContact.setFirstContactedOn(currentTimestamp);
			newUserContact.setLastContactedOn(currentTimestamp);
			newUserContact.setActive(true);
			userContactRepository.save(newUserContact);
			return userContactMapper.mapUserContactToUserContactMessageDetailsDto(newUserContact);
		}
	}

	@Override
	public PostDTO deleteMessage(Long sentBy, Long messageId) {
		// TODO Auto-generated method stub
		return null;
	}

}
