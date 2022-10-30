package com.wearperfect.dataservice.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.UserWorksResponseDTO;
import com.wearperfect.dataservice.api.dto.WorkDTO;
import com.wearperfect.dataservice.api.service.WorkService;

@RestController
public class WorkController {

	@Autowired
	WorkService workService;

	@GetMapping(value = "/users/{userId}/works")
	UserWorksResponseDTO getUserWorkList(@PathVariable(name = "userId") Long userId) {
		return workService.getUserWorkList(userId);
	}

	@PostMapping(value = "/users/{userId}/works")
	UserWorksResponseDTO addUserWork(@PathVariable(name = "userId") Long userId, @RequestBody WorkDTO workDto) {
		return workService.addUserWork(userId, workDto);
	}

	@PutMapping(value = "/users/{userId}/works/{workId}")
	UserWorksResponseDTO updateUserWork(@PathVariable(name = "userId") Long userId, @PathVariable(name = "workId") Long workId,
			@RequestBody WorkDTO workDto) {
		return workService.updateUserWork(userId, workId, workDto);
	}
	
	@DeleteMapping(value = "/users/{userId}/works/{workId}")
	UserWorksResponseDTO deleteUserWork(@PathVariable(name = "userId") Long userId, @PathVariable(name = "workId") Long workId,
			@RequestBody WorkDTO workDto) {
		return workService.deleteUserWork(userId, workId, workDto);
	}
}
