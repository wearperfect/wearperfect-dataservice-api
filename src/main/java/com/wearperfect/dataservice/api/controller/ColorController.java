package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.ColorDTO;
import com.wearperfect.dataservice.api.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ColorController {

    @Autowired
    ColorService colorService;

    @GetMapping(value = "/v1/colors")
    List<ColorDTO> getAllColors() {
        return colorService.getAllColors();
    }

    @GetMapping(value = "/v1/colors/{colorId}")
    ColorDTO getColorByColorId(@PathVariable(name = "colorId") Integer colorId) {
        return colorService.getColorBycolorId(colorId);
    }

    @PostMapping(value = "/v1/colors")
    ColorDTO createColor(@RequestBody ColorDTO colorDto) {
        return colorService.createColor(colorDto);
    }

    @PutMapping(value = "/v1/colors/{colorId}")
    ColorDTO updateColor(@PathVariable(name = "colorId") Integer colorId, @RequestBody ColorDTO colorDto) {
        return colorService.updateColor(colorId, colorDto);
    }

    @DeleteMapping(value = "/v1/colors/{colorId}")
    ColorDTO deleteColor(@PathVariable(name = "colorId") Integer colorId) {
        return colorService.deleteColor(colorId);
    }
}
