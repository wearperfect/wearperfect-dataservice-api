package com.wearperfect.dataservice.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.FiltersResponseDTO;
import com.wearperfect.dataservice.api.service.FilterService;

@RestController
public class FilterController {
	
	@Autowired
	FilterService filterService;
	
	@GetMapping(value = "/filters")
	FiltersResponseDTO getFilters() {
		return filterService.getFilters();
	}
}
