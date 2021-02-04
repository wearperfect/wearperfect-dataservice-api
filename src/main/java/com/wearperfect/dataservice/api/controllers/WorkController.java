package com.wearperfect.dataservice.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.WorkDTO;
import com.wearperfect.dataservice.api.service.WorkService;

@RestController
public class WorkController {
	
	@Autowired
	WorkService workService;

	@GetMapping(value = "/users/{userId}/works")
	List<WorkDTO> getUserWorkList(@PathVariable(name = "userId") Long userId){
		return workService.getUserWorkList(userId);
	}
	
	@PostMapping(value = "/users/{userId}/works")
	List<WorkDTO> addUserWorks(@PathVariable(name = "userId") Long userId,
			@RequestBody List<WorkDTO> workDtos){
		return workService.addUserWorks(userId, workDtos);
	}
}
