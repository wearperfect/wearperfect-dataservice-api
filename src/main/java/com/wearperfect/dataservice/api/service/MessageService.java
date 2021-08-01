package com.wearperfect.dataservice.api.service;

import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.dto.MessageDTO;
import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.UserContactMessageDetailsDTO;

public interface MessageService {

	UserContactMessageDetailsDTO getUserMessagesWith(Long userId, Long targetUserId);

	UserContactMessageDetailsDTO sendMessage(Long sentBy, String name, MessageDTO messageDto, MultipartFile[] files);

	PostDTO deleteMessage(Long sentBy, Long messageId);

}