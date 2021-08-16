package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.MessageDTO;
import com.wearperfect.dataservice.api.dto.UserContactMessagesDTO;

public interface MessageService {

	UserContactMessagesDTO getUserMessagesWith(Long userId, Long targetUserId, Integer page);

	UserContactMessagesDTO sendMessage(Long sentBy, String name, MessageDTO messageDto);

	UserContactMessagesDTO deleteMessage(Long sentBy, Long messageId);

}