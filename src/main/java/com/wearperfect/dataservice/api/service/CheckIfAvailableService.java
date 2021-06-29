package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.UsernameAvailabilityDTO;

public interface CheckIfAvailableService {

	UsernameAvailabilityDTO checkIfUsernameIsAvailable(String username);
}
