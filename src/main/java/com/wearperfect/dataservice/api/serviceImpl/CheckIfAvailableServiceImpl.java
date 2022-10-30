package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.UsernameAvailabilityDTO;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.repository.UserRepository;
import com.wearperfect.dataservice.api.service.CheckIfAvailableService;

@Service
public class CheckIfAvailableServiceImpl implements CheckIfAvailableService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UsernameAvailabilityDTO checkIfUsernameIsAvailable(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		
		UsernameAvailabilityDTO usernameAvailabilityDto = new UsernameAvailabilityDTO();
		usernameAvailabilityDto.setUsername(username);

		if (user.isPresent())
			usernameAvailabilityDto.setAvailable(false);
		else
			usernameAvailabilityDto.setAvailable(true);

		return usernameAvailabilityDto;
	}

}
