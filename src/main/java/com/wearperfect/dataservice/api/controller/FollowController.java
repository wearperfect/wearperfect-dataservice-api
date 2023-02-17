package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.FollowDTO;
import com.wearperfect.dataservice.api.dto.UserFollowsResponseDTO;
import com.wearperfect.dataservice.api.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FollowController {

    @Autowired
    FollowService followService;

    @GetMapping(value = "/v1/users/{userId}/followers")
    @ResponseBody
    public UserFollowsResponseDTO getUserFollowers(@PathVariable(name = "userId") Long userId) {
        return followService.getUserFollowers(userId);
    }

    @GetMapping(value = "/v1/users/{userId}/followings")
    @ResponseBody
    public UserFollowsResponseDTO getUserFollowing(@PathVariable(name = "userId") Long userId) {
        return followService.getUserFollowings(userId);
    }

    @PostMapping(value = "/v1/users/{followingBy}/follows/{userId}")
    @ResponseBody
    public FollowDTO followUser(@PathVariable(name = "followingBy") Long followingBy, @PathVariable(name = "userId") Long userId) {
        return followService.followUser(followingBy, userId);
    }

    @DeleteMapping(value = "/v1/users/{followingBy}/follows/{userId}")
    @ResponseBody
    public FollowDTO unFollowUser(@PathVariable(name = "followingBy") Long followingBy, @PathVariable(name = "userId") Long userId) {
        return followService.unFollowUser(followingBy, userId);
    }

    @DeleteMapping(value = "/v1/users/{followingBy}/follows/{userId}/remove")
    @ResponseBody
    public FollowDTO removeFollower(@PathVariable(name = "followingBy") Long followingBy, @PathVariable(name = "userId") Long userId) {
        return followService.unFollowUser(followingBy, userId);
    }
}
