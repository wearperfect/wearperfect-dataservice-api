package com.wearperfect.dataservice.api.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.dto.MessageDTO;
import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.UserContactMessageDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserPostsResponseDTO;

public interface MessageService {

	List<UserContactMessageDetailsDTO> getUserMessagesContacts(Long sentBy);

	UserPostsResponseDTO getUserMessagesWith(Long sentBy, Long sentTo);

	UserPostsResponseDTO sendMessage(Long sentBy, String name, MessageDTO messageDto, MultipartFile[] files);

	PostDTO deleteMessage(Long sentBy, Long messageId);

}
