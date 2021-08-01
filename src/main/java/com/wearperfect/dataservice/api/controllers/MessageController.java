package com.wearperfect.dataservice.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.dto.MessageDTO;
import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.UserContactMessageDetailsDTO;
import com.wearperfect.dataservice.api.service.MessageService;

@RestController
public class MessageController {

	@Autowired
	MessageService messageService;

	@GetMapping(path = "/users/{userId}/messages/with/{targetUserId}")
	UserContactMessageDetailsDTO getUserMessagesWith(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "targetUserId", required = true) Long targetUserId) {
		return messageService.getUserMessagesWith(userId, targetUserId);
	}

	@PostMapping(path = "/users/{sentBy}/messages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	UserContactMessageDetailsDTO sendMessage(Authentication authentication,
			@PathVariable(name = "sentBy", required = true) Long sentBy,
			@RequestPart(name = "data") MessageDTO messageDto,
			@RequestPart(name = "files") MultipartFile[] files) {
		return messageService.sendMessage(sentBy, authentication.getName(), messageDto, files);
	}

	@DeleteMapping(path = "/users/{sentBy}/messages/{messageId}")
	PostDTO deleteMessage(@PathVariable(name = "sentBy", required = true) Long sentBy,
			@PathVariable(name = "messageId", required = true) Long messageId) {
		return messageService.deleteMessage(sentBy, messageId);
	}
}
