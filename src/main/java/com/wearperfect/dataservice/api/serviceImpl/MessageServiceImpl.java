package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
import com.wearperfect.dataservice.api.dto.UserContactDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserContactMessageDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserPostsResponseDTO;
import com.wearperfect.dataservice.api.entities.UserContact;
import com.wearperfect.dataservice.api.entities.UserContact_;
import com.wearperfect.dataservice.api.mappers.MessageMapper;
import com.wearperfect.dataservice.api.mappers.UserContactMapper;
import com.wearperfect.dataservice.api.repositories.MessageRepository;
import com.wearperfect.dataservice.api.repositories.UserContactRepository;
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

	@Override
	public List<UserContactMessageDetailsDTO> getUserMessagesContacts(Long sentBy) {
		List<UserContact> userContacts = userContactRepository.findByUserId(sentBy,
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
