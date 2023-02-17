package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.FiltersResponseDTO;
import com.wearperfect.dataservice.api.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {

    @Autowired
    FilterService filterService;

    @GetMapping(value = "/v1/filters")
    FiltersResponseDTO getFilters() {
        return filterService.getFilters();
    }
}
