package com.wearperfect.dataservice.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.FeatureBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.RoleDetailsDTO;
import com.wearperfect.dataservice.api.service.SettingsService;

@RestController
public class SettingsController {
	
	@Autowired
	SettingsService settingsService;

	@GetMapping("/settings/roles")
	List<RoleDetailsDTO> getAccountTypes(){
		return settingsService.getSwitchableAccountTypes();
	}
	
	@GetMapping("/settings/features")
	List<FeatureBasicDetailsDTO> getFeatures(){
		return settingsService.getFeatures();
	}
	
}
