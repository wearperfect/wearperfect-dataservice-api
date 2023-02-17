package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.UserWorksResponseDTO;
import com.wearperfect.dataservice.api.dto.WorkDTO;
import com.wearperfect.dataservice.api.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkController {

    @Autowired
    WorkService workService;

    @GetMapping(value = "/v1/users/{userId}/works")
    UserWorksResponseDTO getUserWorkList(@PathVariable(name = "userId") Long userId) {
        return workService.getUserWorkList(userId);
    }

    @PostMapping(value = "/v1/users/{userId}/works")
    UserWorksResponseDTO addUserWork(@PathVariable(name = "userId") Long userId, @RequestBody WorkDTO workDto) {
        return workService.addUserWork(userId, workDto);
    }

    @PutMapping(value = "/v1/users/{userId}/works/{workId}")
    UserWorksResponseDTO updateUserWork(@PathVariable(name = "userId") Long userId, @PathVariable(name = "workId") Long workId, @RequestBody WorkDTO workDto) {
        return workService.updateUserWork(userId, workId, workDto);
    }

    @DeleteMapping(value = "/v1/users/{userId}/works/{workId}")
    UserWorksResponseDTO deleteUserWork(@PathVariable(name = "userId") Long userId, @PathVariable(name = "workId") Long workId, @RequestBody WorkDTO workDto) {
        return workService.deleteUserWork(userId, workId, workDto);
    }
}
