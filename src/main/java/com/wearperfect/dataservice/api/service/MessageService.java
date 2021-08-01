package com.wearperfect.dataservice.api.service;

import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.dto.MessageDTO;
import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.UserContactDetailsDTO;

public interface MessageService {

	UserContactDetailsDTO getUserMessagesWith(Long userId, Long targetUserId);

	UserContactDetailsDTO sendMessage(Long sentBy, String name, MessageDTO messageDto, MultipartFile[] files);

	PostDTO deleteMessage(Long sentBy, Long messageId);

}