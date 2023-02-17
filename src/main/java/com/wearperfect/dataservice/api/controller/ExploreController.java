package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserDetailsDTO;
import com.wearperfect.dataservice.api.service.ExploreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExploreController {

    @Autowired
    ExploreService exploreService;

    @GetMapping(value = "/v1/explore/posts")
    List<PostDetailsDTO> explorePosts() {
        return exploreService.explorePosts();
    }

    @GetMapping(value = "/v1/explore/designers")
    List<UserDetailsDTO> exploreDesigners() {
        return exploreService.exploreDesigners();
    }

    @GetMapping(value = "/v1/explore/brands")
    List<UserDetailsDTO> exploreBrands() {
        return exploreService.exploreBrands();
    }
}
