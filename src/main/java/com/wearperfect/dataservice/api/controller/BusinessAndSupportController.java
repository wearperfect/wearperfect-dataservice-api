package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.BusinessAndSupportDTO;
import com.wearperfect.dataservice.api.service.BusinessAndSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusinessAndSupportController {

    @Autowired
    BusinessAndSupportService businessAndSupportService;

    @GetMapping(value = "/v1/businessandsupport")
    List<BusinessAndSupportDTO> getAllBusinessAndSupport(@RequestParam(name = "page", required = false) Integer page) {
        return businessAndSupportService.getAllBusinessAndSupport(page);
    }

    @GetMapping(value = "/v1/users/{userId}/businessandsupport/{businessAndSupportId}")
    BusinessAndSupportDTO getBusinessAndSupportById(@PathVariable(name = "userId") Long userId, @PathVariable(name = "businessAndSupportId") Long businessAndSupportId) {
        return businessAndSupportService.getBusinessAndSupportById(userId, businessAndSupportId);
    }

    @PostMapping(value = "/v1/users/{userId}/businessandsupport")
    BusinessAndSupportDTO createBusinessAndSupport(@PathVariable(name = "userId") Long userId, @RequestBody BusinessAndSupportDTO businessAndSupportDto) {
        return businessAndSupportService.createBusinessAndSupport(userId, businessAndSupportDto);
    }

    @PutMapping(value = "/v1/users/{userId}/businessandsupport/{businessAndSupportId}")
    BusinessAndSupportDTO updateBusinessAndSupport(@PathVariable(name = "userId") Long userId, @PathVariable(name = "businessAndSupportId") Long businessAndSupportId, @RequestBody BusinessAndSupportDTO businessAndSupportDto) {
        return businessAndSupportService.updateBusinessAndSupport(userId, businessAndSupportId, businessAndSupportDto);
    }

    @PutMapping(value = "/v1/users/{userId}/businessandsupport/{businessAndSupportId}/address")
    BusinessAndSupportDTO updateBusinessAddress(@PathVariable(name = "userId") Long userId, @PathVariable(name = "businessAndSupportId") Long businessAndSupportId, @RequestBody BusinessAndSupportDTO businessAndSupportDto) {
        return businessAndSupportService.updateBusinessAndSupport(userId, businessAndSupportId, businessAndSupportDto);
    }

    @PutMapping(value = "/v1/users/{userId}/businessandsupport/{businessAndSupportId}/support")
    BusinessAndSupportDTO updateBusinessSupport(@PathVariable(name = "userId") Long userId, @PathVariable(name = "businessAndSupportId") Long businessAndSupportId, @RequestBody BusinessAndSupportDTO businessAndSupportDto) {
        return businessAndSupportService.updateBusinessAndSupport(userId, businessAndSupportId, businessAndSupportDto);
    }

    @DeleteMapping(value = "/v1/users/{userId}/businessandsupport/{businessAndSupportId}")
    BusinessAndSupportDTO deleteCategory(@PathVariable(name = "userId") Long userId, @PathVariable(name = "businessAndSupportId") Long businessAndSupportId) {
        return businessAndSupportService.deleteBusinessAndSupport(userId, businessAndSupportId);
    }
}
