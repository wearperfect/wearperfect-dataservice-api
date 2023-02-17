package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.SkillBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserSkillsResponseDTO;
import com.wearperfect.dataservice.api.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkillController {

    @Autowired
    SkillService skillService;

    @GetMapping(path = "/v1/skills")
    List<SkillBasicDetailsDTO> getSkills() {
        return skillService.getSkills();
    }

    @GetMapping(path = "/v1/users/{userId}/skills")
    UserSkillsResponseDTO getUserSkills(@PathVariable(name = "userId", required = true) Long userId) {
        return skillService.getUserSkills(userId);
    }

    @PostMapping(path = "/v1/users/{userId}/skills/{skillId}")
    UserSkillsResponseDTO saveUserSkill(@PathVariable(name = "userId", required = true) Long userId, @PathVariable(name = "skillId", required = true) Integer skillId) {
        return skillService.saveUserSkill(userId, skillId);
    }

    @DeleteMapping(path = "/v1/users/{userId}/skills/{skillId}")
    UserSkillsResponseDTO deleteUserSkill(@PathVariable(name = "userId", required = true) Long userId, @PathVariable(name = "skillId", required = true) Integer skillId) {
        return skillService.deleteUserSkill(userId, skillId);
    }

}
