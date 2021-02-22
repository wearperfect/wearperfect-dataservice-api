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

import com.wearperfect.dataservice.api.dto.CountryDetailsDTO;
import com.wearperfect.dataservice.api.dto.StateBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.StateDTO;
import com.wearperfect.dataservice.api.service.StateService;

@RestController
public class StateController {

	@Autowired
	StateService stateService;
	
	@GetMapping(value = "/states")
	public List<StateBasicDetailsDTO> getAllStates() {
		return stateService.getAllStates();
	}
	
	@GetMapping(value = "/states/{stateId}")
	public StateBasicDetailsDTO getStateDetailsByStateId(@PathVariable(name = "stateId") Integer stateId) {
		return stateService.getStateDetailsByStateId(stateId);
	}
	
	@GetMapping(value = "countries/{countryId}/states")
	public CountryDetailsDTO getAllStatesByCountryId(@PathVariable(name = "countryId") Integer countryId) {
		return stateService.getAllStatesByCountryId(countryId);
	}
	
	@PostMapping(value = "/states")
	public StateBasicDetailsDTO cteateState(@RequestBody StateDTO stateDto) {
		return stateService.cteateState(stateDto);
	}
	
	@PutMapping(value = "/states/{stateId}")
	public StateBasicDetailsDTO updateState(@PathVariable(name = "stateId") Integer stateId,
			@RequestBody StateDTO stateDto) {
		return stateService.updateState(stateId, stateDto);
	}
	
	@DeleteMapping(value = "/states/{stateId}")
	public StateBasicDetailsDTO delelteState(@PathVariable(name = "stateId") Integer stateId) {
		return stateService.delelteState(stateId);
	}
}
