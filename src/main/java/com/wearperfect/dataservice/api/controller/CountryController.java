package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.CountryBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.CountryDTO;
import com.wearperfect.dataservice.api.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping(value = "/v1/countries")
    public List<CountryBasicDetailsDTO> getAllCountries() {
        return countryService.getAllCounries();
    }

    @GetMapping(value = "/v1/countries/{countryId}")
    public CountryBasicDetailsDTO getCountryDetailsByCountryId(@PathVariable(name = "countryId") Integer countryId) {
        return countryService.getCountryDetailsByCountryId(countryId);
    }

    @PostMapping(value = "/v1/countries")
    public CountryBasicDetailsDTO createCountry(@RequestBody CountryDTO countryDto) {
        return countryService.createCountry(countryDto);
    }

    @PutMapping(value = "/v1/countries/{countryId}")
    public CountryBasicDetailsDTO updateCountry(@PathVariable(name = "countryId") Integer countryId, @RequestBody CountryDTO countryDto) {
        return countryService.updateCountry(countryId, countryDto);
    }

    @DeleteMapping(value = "/v1/countries/{countryId}")
    public CountryBasicDetailsDTO deleteCountry(@PathVariable(name = "countryId") Integer countryId) {
        return countryService.deleteCountry(countryId);
    }
}
