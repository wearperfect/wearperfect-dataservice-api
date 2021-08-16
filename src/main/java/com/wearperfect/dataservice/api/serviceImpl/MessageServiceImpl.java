package com.wearperfect.dataservice.api.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.dto.MessageDTO;
import com.wearperfect.dataservice.api.dto.MessageDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserContactMessagesDTO;
import com.wearperfect.dataservice.api.entities.Message;
import com.wearperfect.dataservice.api.entities.Message_;
import com.wearperfect.dataservice.api.entities.UserContact;
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
	public UserContactMessagesDTO getUserMessagesWith(Long userId, Long targetUserId, Integer page) {
		Long[] sentBySentToList = {userId, targetUserId};
		Optional<UserContact> userContact = userContactRepository.findByUserIdAndContactUserId(userId, targetUserId);
		if(userContact.isPresent()) {
			UserContactMessagesDTO userContactMessages = userContactMapper.mapUserContactToUserContactMessagesDto(userContact.get());
			List<Message> messages = messageRepository.findBySentByInAndSentToIn( sentBySentToList, sentBySentToList,
					PageRequest.of(page, 50, Sort.by(Direction.DESC, Message_.CREATED_ON)));
			List<MessageDetailsDTO> userMessages = messages.stream().map(message->messageMapper.mapMessageToMessageDetailsDto(message)).collect(Collectors.toList());
			userContactMessages.setMessages(userMessages);
			return userContactMessages;
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Contact not found.");
		}
		
	}

	@Override
	public UserContactMessagesDTO sendMessage(Long sentBy, String name, MessageDTO messageDto) {
		Date currentTimestamp = new Date();
		UserContactMessagesDTO userContactMessages;
		Message message = messageMapper.mapMessageDtoToMessage(messageDto);
		if (null == message.getSentBy() || null == message.getSentTo()) {
			throw new BadRequestException("sentBy and sentTo fields should not null.");
		}
		message.setCreatedOn(currentTimestamp);
		message = messageRepository.save(message);

		Optional<UserContact> sentByUserContact = userContactRepository.findByUserIdAndContactUserId(message.getSentBy(), message.getSentTo());
		Optional<UserContact> sentToUserContact = userContactRepository.findByUserIdAndContactUserId(message.getSentTo(), message.getSentBy());
		
		if (sentToUserContact.isPresent()) {
			sentToUserContact.get().setLastContactedOn(new Date());
		} else {
			UserContact newUserContact = new UserContact();
			newUserContact.setUserId(messageDto.getSentTo());
			newUserContact.setContactUserId(messageDto.getSentBy());
			newUserContact.setFirstContactedOn(currentTimestamp);
			newUserContact.setLastContactedOn(currentTimestamp);
			newUserContact.setActive(true);
			userContactRepository.save(newUserContact);
		}
		
		if (sentByUserContact.isPresent()) {
			sentByUserContact.get().setLastContactedOn(new Date());
			userContactMessages = userContactMapper.mapUserContactToUserContactMessagesDto(sentByUserContact.get());
		} else {
			UserContact newUserContact = new UserContact();
			newUserContact.setUserId(messageDto.getSentBy());
			newUserContact.setContactUserId(messageDto.getSentTo());
			newUserContact.setFirstContactedOn(currentTimestamp);
			newUserContact.setLastContactedOn(currentTimestamp);
			newUserContact.setActive(true);
			userContactRepository.save(newUserContact);
			userContactMessages = userContactMapper.mapUserContactToUserContactMessagesDto(newUserContact);
		}
		
		List<MessageDetailsDTO> messages = new ArrayList<>();
		messages.add(messageMapper.mapMessageToMessageDetailsDto(message));
		userContactMessages.setMessages(messages);
		return userContactMessages;
	}

	@Override
	public UserContactMessagesDTO deleteMessage(Long sentBy, Long messageId) {
		// TODO Auto-generated method stub
		return null;
	}

}
