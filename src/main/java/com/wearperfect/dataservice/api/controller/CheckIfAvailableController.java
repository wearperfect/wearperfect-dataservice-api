package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.UsernameAvailabilityDTO;
import com.wearperfect.dataservice.api.service.CheckIfAvailableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class CheckIfAvailableController {

    @Autowired
    CheckIfAvailableService checkIfAvailableService;

    @GetMapping(value = "/v1/check-if-available/username")
    public UsernameAvailabilityDTO checkIfUsernameAvailable(@RequestParam(name = "username", required = true) String username) {
        if (null == username || username.trim().length() < 3) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Username should not be empty and have atleast 3 characters.");
        }
        return checkIfAvailableService.checkIfUsernameIsAvailable(username);
    }

}
