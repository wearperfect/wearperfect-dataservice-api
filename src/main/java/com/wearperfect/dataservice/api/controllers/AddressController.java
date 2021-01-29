package com.wearperfect.dataservice.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.AddressDTO;
import com.wearperfect.dataservice.api.dto.UserDTO;
import com.wearperfect.dataservice.api.dto.UserDetailsDTO;
import com.wearperfect.dataservice.api.entities.User;
import com.wearperfect.dataservice.api.mappers.UserMapper;
import com.wearperfect.dataservice.api.service.AddressService;
import com.wearperfect.dataservice.api.service.UserService;

@RestController
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@GetMapping(value = "/users/{userId}/addresses")
	public List<AddressDTO> getAllUserAddresses(@PathVariable(name = "userId") Long userId) {
		return addressService.getAllUserAddresses(userId);
	}
}
