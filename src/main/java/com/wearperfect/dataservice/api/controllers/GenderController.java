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

import com.wearperfect.dataservice.api.dto.GenderCategoryDTO;
import com.wearperfect.dataservice.api.dto.GenderDTO;
import com.wearperfect.dataservice.api.service.GenderService;

@RestController
public class GenderController {
	
	@Autowired
	GenderService genderService;

	// Genders
	
	@GetMapping(value = "/genders")
	List<GenderDTO> getAllgenders() {
		return genderService.getAllgenders();
	}

	@GetMapping(value = "/genders/{genderId}")
	GenderDTO getGenderBygenderId(@PathVariable(name = "genderId") Integer genderId) {
		return genderService.getGenderBygenderId(genderId);
	}

	@PostMapping(value = "/genders")
	GenderDTO createGender(@RequestBody GenderDTO genderDto) {
		return genderService.createGender(genderDto);
	}

	@PutMapping(value = "/genders/{genderId}")
	GenderDTO updateGender(@PathVariable(name = "genderId") Integer genderId, @RequestBody GenderDTO genderDto) {
		return genderService.updateGender(genderId, genderDto);
	}

	@DeleteMapping(value = "/genders/{genderId}")
	GenderDTO deleteGender(@PathVariable(name = "genderId") Integer genderId) {
		return genderService.deleteGender(genderId);
	}
	
	// GenderCategories
	
	@GetMapping(value = "/genders/categories")
	List<GenderCategoryDTO> getAllGenderCategories() {
		return genderService.getAllGenderCategories();
	}

	@GetMapping(value = "/genders/categories/{genderCategoryId}")
	GenderCategoryDTO getGenderCategoryByGenderCategoryId(@PathVariable(name = "genderCategoryId") Integer genderCategoryId) {
		return genderService.getGenderCategoryByGenderCategoryId(genderCategoryId);
	}

	@PostMapping(value = "/genders/categories")
	GenderCategoryDTO createGenderCategory(@RequestBody GenderCategoryDTO genderCategoryDto) {
		return genderService.createGenderCategory(genderCategoryDto);
	}

	@PutMapping(value = "/genders/categories/{genderCategoryId}")
	GenderCategoryDTO updateGenderCategory(@PathVariable(name = "genderCategoryId") Integer genderCategoryId, @RequestBody GenderCategoryDTO genderCategoryDto) {
		return genderService.updateGenderCategory(genderCategoryId, genderCategoryDto);
	}

	@DeleteMapping(value = "/genders/categories/{genderCategoryId}")
	GenderCategoryDTO deleteGenderCategory(@PathVariable(name = "genderCategoryId") Integer genderCategoryId) {
		return genderService.deleteGenderCategory(genderCategoryId);
	}
}
