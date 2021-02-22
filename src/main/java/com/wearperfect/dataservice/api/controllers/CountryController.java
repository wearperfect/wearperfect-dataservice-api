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

import com.wearperfect.dataservice.api.dto.CountryBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.CountryDTO;
import com.wearperfect.dataservice.api.service.CountryService;

@RestController
public class CountryController {

	@Autowired
	CountryService countryService;

	@GetMapping(value = "/countries")
	public List<CountryBasicDetailsDTO> getAllCountries() {
		return countryService.getAllCounries();
	}
	
	@GetMapping(value = "/countries/{countryId}")
	public CountryBasicDetailsDTO getCountryDetailsByCountryId(@PathVariable(name = "countryId") Integer countryId) {
		return countryService.getCountryDetailsByCountryId(countryId);
	}
	
	@PostMapping(value = "/countries")
	public CountryBasicDetailsDTO createCountry(@RequestBody CountryDTO countryDto) {
		return countryService.createCountry(countryDto);
	}
	
	@PutMapping(value = "/countries/{countryId}")
	public CountryBasicDetailsDTO updateCountry(
			@PathVariable(name = "countryId") Integer countryId,
			@RequestBody CountryDTO countryDto) {
		return countryService.updateCountry(countryId, countryDto);
	}
	
	@DeleteMapping(value = "/countries/{countryId}")
	public CountryBasicDetailsDTO deleteCountry(@PathVariable(name = "countryId") Integer countryId) {
		return countryService.deleteCountry(countryId);
	}
}
