package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.GenderCategoryDTO;
import com.wearperfect.dataservice.api.dto.GenderDTO;
import com.wearperfect.dataservice.api.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenderController {

    @Autowired
    GenderService genderService;

    // Genders

    @GetMapping(value = "/v1/genders")
    List<GenderDTO> getAllGenders() {
        return genderService.getAllgenders();
    }

    @GetMapping(value = "/v1/genders/{genderId}")
    GenderDTO getGenderByGenderId(@PathVariable(name = "genderId") Integer genderId) {
        return genderService.getGenderBygenderId(genderId);
    }

    @PostMapping(value = "/v1/genders")
    GenderDTO createGender(@RequestBody GenderDTO genderDto) {
        return genderService.createGender(genderDto);
    }

    @PutMapping(value = "/v1/genders/{genderId}")
    GenderDTO updateGender(@PathVariable(name = "genderId") Integer genderId, @RequestBody GenderDTO genderDto) {
        return genderService.updateGender(genderId, genderDto);
    }

    @DeleteMapping(value = "/v1/genders/{genderId}")
    GenderDTO deleteGender(@PathVariable(name = "genderId") Integer genderId) {
        return genderService.deleteGender(genderId);
    }

    // GenderCategories

    @GetMapping(value = "/v1/genders/categories")
    List<GenderCategoryDTO> getAllGenderCategories() {
        return genderService.getAllGenderCategories();
    }

    @GetMapping(value = "/v1/genders/categories/{genderCategoryId}")
    GenderCategoryDTO getGenderCategoryByGenderCategoryId(@PathVariable(name = "genderCategoryId") Integer genderCategoryId) {
        return genderService.getGenderCategoryByGenderCategoryId(genderCategoryId);
    }

    @PostMapping(value = "/v1/genders/categories")
    GenderCategoryDTO createGenderCategory(@RequestBody GenderCategoryDTO genderCategoryDto) {
        return genderService.createGenderCategory(genderCategoryDto);
    }

    @PutMapping(value = "/v1/genders/categories/{genderCategoryId}")
    GenderCategoryDTO updateGenderCategory(@PathVariable(name = "genderCategoryId") Integer genderCategoryId, @RequestBody GenderCategoryDTO genderCategoryDto) {
        return genderService.updateGenderCategory(genderCategoryId, genderCategoryDto);
    }

    @DeleteMapping(value = "/v1/genders/categories/{genderCategoryId}")
    GenderCategoryDTO deleteGenderCategory(@PathVariable(name = "genderCategoryId") Integer genderCategoryId) {
        return genderService.deleteGenderCategory(genderCategoryId);
    }
}
