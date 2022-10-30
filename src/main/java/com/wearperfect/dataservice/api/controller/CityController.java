package com.wearperfect.dataservice.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.CityBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.CityDTO;
import com.wearperfect.dataservice.api.dto.StateDetailsDTO;
import com.wearperfect.dataservice.api.service.CityService;

@RestController
public class CityController {
	
	@Autowired
	CityService cityService;

	@GetMapping(value = "/cities")
	public List<CityBasicDetailsDTO> getAllCities() {
		return cityService.getAllCities();
	}
	
	@GetMapping(value = "/cities/{cityId}")
	public CityBasicDetailsDTO getCityDetailsByCityId(@PathVariable(name = "cityId") Integer cityId) {
		return cityService.getCityDetailsByCityId(cityId);
	}
	
	@GetMapping(value = "/states/{stateId}/cities")
	public StateDetailsDTO getAllCitiesByStateId(@PathVariable(name = "stateId") Integer stateId) {
		return cityService.getAllCitiesByStateId(stateId);
	}
	
	@PostMapping(value = "/cities")
	public CityBasicDetailsDTO createCity(@RequestBody CityDTO cityDto) {
		return cityService.createCity(cityDto);
	}
	
	@PutMapping(value = "/cities/{cityId}")
	public CityBasicDetailsDTO updateCity(@PathVariable(name = "cityId") Integer cityId,
			@RequestBody CityDTO cityDto) {
		return cityService.updateCity(cityId, cityDto);
	}
	
	@DeleteMapping(value = "/cities/{cityId}")
	public CityBasicDetailsDTO deleteCity(@PathVariable(name = "cityId") Integer cityId) {
		return cityService.deleteCity(cityId);
	}
	
}
