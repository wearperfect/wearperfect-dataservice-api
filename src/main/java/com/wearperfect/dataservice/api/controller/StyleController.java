package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.StyleBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserStylesResponseDTO;
import com.wearperfect.dataservice.api.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StyleController {

    @Autowired
    StyleService styleService;

    @GetMapping(path = "/v1/styles")
    List<StyleBasicDetailsDTO> getStyles() {
        return styleService.getStyles();
    }

    @GetMapping(path = "/v1/users/{userId}/styles")
    UserStylesResponseDTO getUserStyles(@PathVariable(name = "userId", required = true) Long userId) {
        return styleService.getUserStyles(userId);
    }

    @PostMapping(path = "/v1/users/{userId}/styles/{styleId}")
    UserStylesResponseDTO saveUserStyle(@PathVariable(name = "userId", required = true) Long userId, @PathVariable(name = "styleId", required = true) Integer styleId) {
        return styleService.saveUserStyle(userId, styleId);
    }

    @DeleteMapping(path = "/v1/users/{userId}/styles/{styleId}")
    UserStylesResponseDTO deleteUserStyle(@PathVariable(name = "userId", required = true) Long userId, @PathVariable(name = "styleId", required = true) Integer styleId) {
        return styleService.deleteUserStyle(userId, styleId);
    }
}
