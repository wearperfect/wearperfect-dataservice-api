package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.SearchResponseDTO;
import com.wearperfect.dataservice.api.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping(path = "/v1/search")
    SearchResponseDTO search(@RequestParam(name = "realm", required = true) String realm, @RequestParam(name = "query", required = true) String query, @RequestParam(name = "strictMode", required = false) Boolean strictMode) {
        if (strictMode == null) {
            strictMode = false;
        }
        return searchService.search(realm, query, strictMode);
    }
}
