package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.dto.MessageDTO;
import com.wearperfect.dataservice.api.dto.MessageDetailsDTO;
import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.UserContactDetailsDTO;
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
	public UserContactDetailsDTO getUserMessagesWith(Long userId, Long targetUserId) {
		Long[] sentBySentToList = {userId, targetUserId};
		List<Message> messages = messageRepository.findBySentByInAndSentToIn( sentBySentToList, sentBySentToList,
				PageRequest.of(0, 50, Sort.by(Direction.ASC, Message_.CREATED_ON)));
		Optional<UserContact> userContact = userContactRepository.findByUserIdInAndContactUserIdIn(sentBySentToList, sentBySentToList);
		if(userContact.isPresent()) {
			UserContactDetailsDTO userContactMessageDetails = userContactMapper.mapUserContactToUserContactDetailsDto(userContact.get());
			List<MessageDetailsDTO> userMessages = messages.stream().map(message->messageMapper.mapMessageToMessageDetailsDto(message)).collect(Collectors.toList());
			userContactMessageDetails.setMessages(userMessages);
			return userContactMessageDetails;
		}else {
			return new UserContactDetailsDTO();
		}
		
	}

	@Override
	public UserContactDetailsDTO sendMessage(Long sentBy, String name, MessageDTO messageDto,
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
			return userContactMapper.mapUserContactToUserContactDetailsDto(userContact.get());
		} else {
			UserContact newUserContact = new UserContact();
			newUserContact.setUserId(messageDto.getSentBy());
			newUserContact.setContactUserId(messageDto.getSentTo());
			newUserContact.setFirstContactedOn(currentTimestamp);
			newUserContact.setLastContactedOn(currentTimestamp);
			newUserContact.setActive(true);
			userContactRepository.save(newUserContact);
			return userContactMapper.mapUserContactToUserContactDetailsDto(newUserContact);
		}
	}

	@Override
	public PostDTO deleteMessage(Long sentBy, Long messageId) {
		// TODO Auto-generated method stub
		return null;
	}

}
