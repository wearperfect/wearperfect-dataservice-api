package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.FeatureBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.RoleDetailsDTO;
import com.wearperfect.dataservice.api.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SettingsController {

    @Autowired
    SettingsService settingsService;

    @GetMapping("/v1/settings/roles")
    List<RoleDetailsDTO> getAccountTypes() {
        return settingsService.getSwitchableAccountTypes();
    }

    @GetMapping("/v1/settings/features")
    List<FeatureBasicDetailsDTO> getFeatures() {
        return settingsService.getFeatures();
    }

}
