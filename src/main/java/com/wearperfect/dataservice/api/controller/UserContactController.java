package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.UserContactMessagesDTO;
import com.wearperfect.dataservice.api.dto.UserContactSuggestionsDTO;
import com.wearperfect.dataservice.api.service.UserContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserContactController {

    @Autowired
    UserContactService userContactService;

    @GetMapping(path = "/v1/users/{userId}/contacts/suggested")
    UserContactSuggestionsDTO getUserContactSuggestionsByUserId(@PathVariable(name = "userId", required = true) Long userId) {
        return userContactService.getSuggestedContacts(userId);
    }

    @GetMapping(path = "/v1/users/{userId}/contacts/communicated")
    List<UserContactMessagesDTO> getCommunicatedUserContactsByUserId(@PathVariable(name = "userId", required = true) Long userId) {
        return userContactService.getCommunicatedContacts(userId);
    }

}
