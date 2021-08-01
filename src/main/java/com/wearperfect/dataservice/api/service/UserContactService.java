package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.UserContactMessageDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserContactSuggestionsDTO;

public interface UserContactService {

	UserContactSuggestionsDTO getSuggestedContacts(Long userId);

	List<UserContactMessageDetailsDTO> getCommunicatedContacts(Long userId);

}
