package com.wearperfect.dataservice.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.BusinessAndSupportDTO;
import com.wearperfect.dataservice.api.service.BusinessAndSupportService;

@RestController
public class BusinessAndSupportController {
	
	@Autowired
	BusinessAndSupportService businessAndSupportService;

	@GetMapping(value = "/businessandsupport")
	List<BusinessAndSupportDTO> getAllBusinessAndSupport(@RequestParam(name="page", required = false) Integer page) {
		return businessAndSupportService.getAllBusinessAndSupport(page);
	}

	@GetMapping(value = "/users/{userId}/businessandsupport/{businessAndSupportId}")
	BusinessAndSupportDTO getBusinessAndSupportById(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "businessAndSupportId") Long businessAndSupportId) {
		return businessAndSupportService.getBusinessAndSupportById(userId, businessAndSupportId);
	}

	@PostMapping(value = "/users/{userId}/businessandsupport")
	BusinessAndSupportDTO createBusinessAndSupport(@PathVariable(name = "userId") Long userId, 
			@RequestBody BusinessAndSupportDTO businessAndSupportDto) {
		return businessAndSupportService.createBusinessAndSupport(userId, businessAndSupportDto);
	}

	@PutMapping(value = "/users/{userId}/businessandsupport/{businessAndSupportId}")
	BusinessAndSupportDTO updateBusinessAndSupport(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "businessAndSupportId") Long businessAndSupportId,
			@RequestBody BusinessAndSupportDTO businessAndSupportDto) {
		return businessAndSupportService.updateBusinessAndSupport(userId, businessAndSupportId, businessAndSupportDto);
	}
	
	@PutMapping(value = "/users/{userId}/businessandsupport/{businessAndSupportId}/address")
	BusinessAndSupportDTO updateBusinessAddress(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "businessAndSupportId") Long businessAndSupportId,
			@RequestBody BusinessAndSupportDTO businessAndSupportDto) {
		return businessAndSupportService.updateBusinessAndSupport(userId, businessAndSupportId, businessAndSupportDto);
	}
	
	@PutMapping(value = "/users/{userId}/businessandsupport/{businessAndSupportId}/support")
	BusinessAndSupportDTO updateBusinessSupport(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "businessAndSupportId") Long businessAndSupportId,
			@RequestBody BusinessAndSupportDTO businessAndSupportDto) {
		return businessAndSupportService.updateBusinessAndSupport(userId, businessAndSupportId, businessAndSupportDto);
	}

	@DeleteMapping(value = "/users/{userId}/businessandsupport/{businessAndSupportId}")
	BusinessAndSupportDTO deleteCategory(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "businessAndSupportId") Long businessAndSupportId) {
		return businessAndSupportService.deleteBusinessAndSupport(userId, businessAndSupportId);
	}
}
