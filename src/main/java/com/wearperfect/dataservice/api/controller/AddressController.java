package com.wearperfect.dataservice.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.AddressDTO;
import com.wearperfect.dataservice.api.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@GetMapping(value = "/users/{userId}/addresses")
	public List<AddressDTO> getAllUserAddresses(@PathVariable(name = "userId") Long userId) {
		return addressService.getAllUserAddresses(userId);
	}
	
	@GetMapping(value = "/users/{userId}/basicinfo/contact/addresses")
	public List<AddressDTO> getUserAddresses(@PathVariable(name = "userId") Long userId) {
		return addressService.getUserBasicInfoContactAddresses(userId);
	}
}
