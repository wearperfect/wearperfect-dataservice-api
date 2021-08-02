package com.wearperfect.dataservice.api.service;

import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.dto.MessageDTO;
import com.wearperfect.dataservice.api.dto.UserContactMessagesDTO;

public interface MessageService {

	UserContactMessagesDTO getUserMessagesWith(Long userId, Long targetUserId);

	UserContactMessagesDTO sendMessage(Long sentBy, String name, MessageDTO messageDto, MultipartFile[] files);

	UserContactMessagesDTO deleteMessage(Long sentBy, Long messageId);

}