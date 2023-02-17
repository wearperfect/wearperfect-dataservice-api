package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.CountryDetailsDTO;
import com.wearperfect.dataservice.api.dto.StateBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.StateDTO;
import com.wearperfect.dataservice.api.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StateController {

    @Autowired
    StateService stateService;

    @GetMapping(value = "/v1/states")
    public List<StateBasicDetailsDTO> getAllStates() {
        return stateService.getAllStates();
    }

    @GetMapping(value = "/v1/states/{stateId}")
    public StateBasicDetailsDTO getStateDetailsByStateId(@PathVariable(name = "stateId") Integer stateId) {
        return stateService.getStateDetailsByStateId(stateId);
    }

    @GetMapping(value = "/v1/countries/{countryId}/states")
    public CountryDetailsDTO getAllStatesByCountryId(@PathVariable(name = "countryId") Integer countryId) {
        return stateService.getAllStatesByCountryId(countryId);
    }

    @PostMapping(value = "/v1/states")
    public StateBasicDetailsDTO cteateState(@RequestBody StateDTO stateDto) {
        return stateService.cteateState(stateDto);
    }

    @PutMapping(value = "/v1/states/{stateId}")
    public StateBasicDetailsDTO updateState(@PathVariable(name = "stateId") Integer stateId, @RequestBody StateDTO stateDto) {
        return stateService.updateState(stateId, stateDto);
    }

    @DeleteMapping(value = "/v1/states/{stateId}")
    public StateBasicDetailsDTO deleteState(@PathVariable(name = "stateId") Integer stateId) {
        return stateService.delelteState(stateId);
    }
}
