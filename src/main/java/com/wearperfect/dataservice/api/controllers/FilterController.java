package com.wearperfect.dataservice.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.FiltersResponseDTO;
import com.wearperfect.dataservice.api.dto.SavedFilterDTO;
import com.wearperfect.dataservice.api.dto.SavedFilterDetailsDTO;
import com.wearperfect.dataservice.api.service.FilterService;

@RestController
public class FilterController {
	
	@Autowired
	FilterService filterService;
	
	@GetMapping(value = "/filters")
	FiltersResponseDTO getFilters() {
		return filterService.getFilters();
	}
	
	@GetMapping(value = "/users/{userId}/preferences/filters")
	List<SavedFilterDetailsDTO> getUserSavedFilters(@PathVariable(name = "userId") Long userId) {
		return filterService.getUserSavedFilters(userId);
	}
	
	@GetMapping(value = "/users/{userId}/preferences/filters/{filterId}")
	SavedFilterDetailsDTO updateUserPreferenceFilterByIdAndUSerId(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId) {
		return filterService.getUserSavedFilterByIdAndUserId(filterId, userId);
	}
	
	@PostMapping(value = "/users/{userId}/preferences/filters")
	SavedFilterDetailsDTO addUserSavedFilters(@PathVariable(name = "userId") Long userId, @RequestBody SavedFilterDTO savedFilterDto) {
		return filterService.addUserSavedFilters(userId, savedFilterDto);
	}
	
	@PutMapping(value = "/users/{userId}/preferences/filters/{filterId}")
	SavedFilterDTO updateUserPreferenceFilter(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId,
			@RequestBody SavedFilterDTO savedFilterDto) {
		return filterService.updateUserPreferenceFilter(userId, filterId, savedFilterDto);
	}
	
	@DeleteMapping(value = "/users/{userId}/preferences/filters/{filterId}")
	SavedFilterDTO deleteUserPreferenceFilter(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId) {
		return filterService.deleteUserPreferenceFilter(userId, filterId);
	}
}
