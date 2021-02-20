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

import com.wearperfect.dataservice.api.dto.PreferenceFilterCategoryDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterDetailsDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterDTO;
import com.wearperfect.dataservice.api.service.PreferenceFilterService;

@RestController
public class PreferenceFilterController {
	
	@Autowired
	PreferenceFilterService preferenceFilterService;

	@GetMapping(value = "/users/{userId}/preferences/filters")
	List<PreferenceFilterDetailsDTO> getUserSavedFilters(@PathVariable(name = "userId") Long userId) {
		return preferenceFilterService.getUserSavedFilters(userId);
	}
	
	@GetMapping(value = "/users/{userId}/preferences/filters/{filterId}")
	PreferenceFilterDetailsDTO updateUserPreferenceFilterByIdAndUSerId(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId) {
		return preferenceFilterService.getUserSavedFilterByIdAndUserId(filterId, userId);
	}
	
	@PostMapping(value = "/users/{userId}/preferences/filters")
	PreferenceFilterDetailsDTO addUserSavedFilters(@PathVariable(name = "userId") Long userId, @RequestBody PreferenceFilterDTO savedFilterDto) {
		return preferenceFilterService.addUserSavedFilters(userId, savedFilterDto);
	}
	
	@PutMapping(value = "/users/{userId}/preferences/filters/{filterId}")
	PreferenceFilterDTO updateUserPreferenceFilter(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId,
			@RequestBody PreferenceFilterDTO savedFilterDto) {
		return preferenceFilterService.updateUserPreferenceFilter(userId, filterId, savedFilterDto);
	}
	
	@DeleteMapping(value = "/users/{userId}/preferences/filters/{filterId}")
	PreferenceFilterDTO deleteUserPreferenceFilter(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId) {
		return preferenceFilterService.deleteUserPreferenceFilter(userId, filterId);
	}
	
	@PostMapping(value = "/users/{userId}/preferences/filters/{filterId}/categories/{categoryId}")
	PreferenceFilterCategoryDTO addCategoryToPreferenceFilter(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId,
			@PathVariable(name = "categoryId") Long categoryId) {
		return preferenceFilterService.addCategoryToPreferenceFilter(userId, filterId, categoryId);
	}
	
	@DeleteMapping(value = "/users/{userId}/preferences/filters/{filterId}/categories/{categoryId}")
	PreferenceFilterCategoryDTO deleteCategoryToPreferenceFilter(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId,
			@PathVariable(name = "categoryId") Long categoryId) {
		return preferenceFilterService.deleteCategoryFromPreferenceFilter(userId, filterId, categoryId);
	}
	
	@PostMapping(value = "/users/{userId}/preferences/filters/{filterId}/colors/{colorId}")
	PreferenceFilterCategoryDTO addColorToPreferenceFilter(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId,
			@PathVariable(name = "colorId") Long colorId) {
		return preferenceFilterService.addColorToPreferenceFilter(userId, filterId, colorId);
	}
	
	@DeleteMapping(value = "/users/{userId}/preferences/filters/{filterId}/colors/{colorId}")
	PreferenceFilterCategoryDTO deleteColorToPreferenceFilter(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId,
			@PathVariable(name = "colorId") Long colorId) {
		return preferenceFilterService.deleteColorFromPreferenceFilter(userId, filterId, colorId);
	}
	
	@PostMapping(value = "/users/{userId}/preferences/filters/{filterId}/genders/categories/{genderCategoryId}")
	PreferenceFilterCategoryDTO addGenderCategoryToPreferenceFilter(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId,
			@PathVariable(name = "genderCategoryId") Long genderCategoryId) {
		return preferenceFilterService.addGenderCategoryToPreferenceFilter(userId, filterId, genderCategoryId);
	}
	
	@DeleteMapping(value = "/users/{userId}/preferences/filters/{filterId}/genders/categories/{genderCategoryId}")
	PreferenceFilterCategoryDTO deleteGenderCategoryToPreferenceFilter(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId,
			@PathVariable(name = "genderCategoryId") Long genderCategoryId) {
		return preferenceFilterService.deleteGenderCategoryFromPreferenceFilter(userId, filterId, genderCategoryId);
	}
	
	@PostMapping(value = "/users/{userId}/preferences/filters/{filterId}/regions/{regionId}")
	PreferenceFilterCategoryDTO addRegionToPreferenceFilter(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId,
			@PathVariable(name = "regionId") Long regionId) {
		return preferenceFilterService.addRegionToPreferenceFilter(userId, filterId, regionId);
	}
	
	@DeleteMapping(value = "/users/{userId}/preferences/filters/{filterId}/regions/{regionId}")
	PreferenceFilterCategoryDTO deleteRegionFromPreferenceFilter(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId,
			@PathVariable(name = "regionId") Long regionId) {
		return preferenceFilterService.deleteRegionFromPreferenceFilter(userId, filterId, regionId);
	}
	
	@PostMapping(value = "/users/{userId}/preferences/filters/{filterId}/styles/{styleId}")
	PreferenceFilterCategoryDTO addStyleToPreferenceFilter(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId,
			@PathVariable(name = "styleId") Long styleId) {
		return preferenceFilterService.addStyleToPreferenceFilter(userId, filterId, styleId);
	}
	
	@DeleteMapping(value = "/users/{userId}/preferences/filters/{filterId}/styles/{styleId}")
	PreferenceFilterCategoryDTO deleteStyleFromPreferenceFilter(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId,
			@PathVariable(name = "styleId") Long styleId) {
		return preferenceFilterService.deleteStyleFromPreferenceFilter(userId, filterId, styleId);
	}
	
	@PostMapping(value = "/users/{userId}/preferences/filters/{filterId}/users/{preferredUserId}")
	PreferenceFilterCategoryDTO addUserToPreferenceFilter(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId,
			@PathVariable(name = "preferredUserId") Long preferredUserId) {
		return preferenceFilterService.addUserToPreferenceFilter(userId, filterId, preferredUserId);
	}
	
	@DeleteMapping(value = "/users/{userId}/preferences/filters/{filterId}/users/{preferredUserId}")
	PreferenceFilterCategoryDTO deleteUserFromPreferenceFilter(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "filterId") Long filterId,
			@PathVariable(name = "preferredUserId") Long preferredUserId) {
		return preferenceFilterService.deleteUserFromPreferenceFilter(userId, filterId, preferredUserId);
	}
}
