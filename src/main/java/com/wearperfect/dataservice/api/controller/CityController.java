package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.CityBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.CityDTO;
import com.wearperfect.dataservice.api.dto.StateDetailsDTO;
import com.wearperfect.dataservice.api.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    CityService cityService;

    @GetMapping(value = "/v1/cities")
    public List<CityBasicDetailsDTO> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping(value = "/v1/cities/{cityId}")
    public CityBasicDetailsDTO getCityDetailsByCityId(@PathVariable(name = "cityId") Integer cityId) {
        return cityService.getCityDetailsByCityId(cityId);
    }

    @GetMapping(value = "/v1/states/{stateId}/cities")
    public StateDetailsDTO getAllCitiesByStateId(@PathVariable(name = "stateId") Integer stateId) {
        return cityService.getAllCitiesByStateId(stateId);
    }

    @PostMapping(value = "/v1/cities")
    public CityBasicDetailsDTO createCity(@RequestBody CityDTO cityDto) {
        return cityService.createCity(cityDto);
    }

    @PutMapping(value = "/v1/cities/{cityId}")
    public CityBasicDetailsDTO updateCity(@PathVariable(name = "cityId") Integer cityId, @RequestBody CityDTO cityDto) {
        return cityService.updateCity(cityId, cityDto);
    }

    @DeleteMapping(value = "/v1/cities/{cityId}")
    public CityBasicDetailsDTO deleteCity(@PathVariable(name = "cityId") Integer cityId) {
        return cityService.deleteCity(cityId);
    }

}
