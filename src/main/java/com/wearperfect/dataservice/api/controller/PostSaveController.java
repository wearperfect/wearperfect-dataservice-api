package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.PostSaveDTO;
import com.wearperfect.dataservice.api.dto.PostSaveDetailsDTO;
import com.wearperfect.dataservice.api.service.PostSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostSaveController {

    @Autowired
    PostSaveService postSaveService;

    @GetMapping(path = "/v1/posts/{postId}/saves")
    List<PostSaveDTO> postSaves(@PathVariable(name = "postId", required = true) Long postId) {
        return postSaveService.postSaves(postId);
    }

    @PostMapping(path = "/v1/users/{userId}/posts/{postId}/saves")
    PostSaveDetailsDTO savePost(@PathVariable(name = "userId", required = true) Long userId, @PathVariable(name = "postId", required = true) Long postId) {
        return postSaveService.savePost(userId, postId);
    }

    @DeleteMapping(path = "/v1/users/{userId}/posts/{postId}/saves")
    PostDTO unSavePost(@PathVariable(name = "userId", required = true) Long userId, @PathVariable(name = "postId", required = true) Long postId) {
        return postSaveService.unSavePost(userId, postId);
    }
}
