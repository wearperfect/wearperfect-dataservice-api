package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.PostLikeDTO;
import com.wearperfect.dataservice.api.service.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostLikeController {

    @Autowired
    PostLikeService postLikeService;

    @GetMapping(path = "/v1/posts/{postId}/likes")
    List<PostLikeDTO> postLikes(@PathVariable(name = "postId", required = true) Long postId) {
        return postLikeService.postLikes(postId);
    }

    @GetMapping(path = "/v1/users/{userId}/posts/{postId}/likes")
    Boolean isPostLikedByUserId(@PathVariable(name = "userId", required = true) Long userId,
                                @PathVariable(name = "postId", required = true) Long postId) {
        return postLikeService.isPostLikedByUserId(userId, postId);
    }

    @PostMapping(path = "/v1/users/{userId}/posts/{postId}/likes")
    Long likePost(@PathVariable(name = "userId", required = true) Long userId,
                  @PathVariable(name = "postId", required = true) Long postId) {
        return postLikeService.likePost(userId, postId);
    }

    @DeleteMapping(path = "/v1/users/{userId}/posts/{postId}/likes")
    Long unLikePost(@PathVariable(name = "userId", required = true) Long userId,
                    @PathVariable(name = "postId", required = true) Long postId) {
        return postLikeService.unLikePost(userId, postId);
    }
}
