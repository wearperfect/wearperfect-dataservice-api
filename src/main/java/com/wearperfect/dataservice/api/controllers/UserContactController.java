package com.wearperfect.dataservice.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.UserContactDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserContactSuggestionsDTO;
import com.wearperfect.dataservice.api.service.UserContactService;

@RestController
public class UserContactController {
	
	@Autowired
	UserContactService userContactService;

	@GetMapping(path = "/users/{userId}/contacts/suggested")
	UserContactSuggestionsDTO getUserContactSuggestionsByUserId(@PathVariable(name = "userId", required = true) Long userId) {
		return userContactService.getSuggestedContacts(userId);
	}
	
	@GetMapping(path = "/users/{userId}/contacts/communicated")
	List<UserContactDetailsDTO> getCommunicatedUserContactsByUserId(@PathVariable(name = "userId", required = true) Long userId) {
		return userContactService.getCommunicatedContacts(userId);
	}
	
}
