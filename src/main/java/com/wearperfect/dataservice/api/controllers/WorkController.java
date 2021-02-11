package com.wearperfect.dataservice.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.WorkDTO;
import com.wearperfect.dataservice.api.service.WorkService;

@RestController
public class WorkController {

	@Autowired
	WorkService workService;

	@GetMapping(value = "/users/{userId}/works")
	List<WorkDTO> getUserWorkList(@PathVariable(name = "userId") Long userId) {
		return workService.getUserWorkList(userId);
	}

	@PostMapping(value = "/users/{userId}/works")
	WorkDTO addUserWorks(@PathVariable(name = "userId") Long userId, @RequestBody WorkDTO workDto) {
		return workService.addUserWork(userId, workDto);
	}

	@PutMapping(value = "/users/{userId}/works/{workId}")
	WorkDTO updateUserWorks(@PathVariable(name = "userId") Long userId, @PathVariable(name = "workId") Long workId,
			@RequestBody WorkDTO workDto) {
		return workService.updateUserWork(userId, workId, workDto);
	}
}
