package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.MessageDTO;
import com.wearperfect.dataservice.api.dto.UserContactMessagesDTO;
import com.wearperfect.dataservice.api.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping(path = "/v1/users/{userId}/messages/with/{targetUserId}")
    UserContactMessagesDTO getUserMessagesWith(@PathVariable(name = "userId", required = true) Long userId, @PathVariable(name = "targetUserId", required = true) Long targetUserId) {
        return messageService.getUserMessagesWith(userId, targetUserId, 0);
    }

    @PostMapping(path = "/v1/users/{sentBy}/messages")
    UserContactMessagesDTO sendMessage(Authentication authentication, @PathVariable(name = "sentBy", required = true) Long sentBy, @RequestBody MessageDTO messageDto) {
        return messageService.sendMessage(sentBy, authentication.getName(), messageDto);
    }

    @DeleteMapping(path = "/v1/users/{sentBy}/messages/{messageId}")
    UserContactMessagesDTO deleteMessage(@PathVariable(name = "sentBy", required = true) Long sentBy, @PathVariable(name = "messageId", required = true) Long messageId) {
        return messageService.deleteMessage(sentBy, messageId);
    }
}
