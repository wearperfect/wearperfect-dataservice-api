package com.wearperfect.dataservice.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.ColorDTO;
import com.wearperfect.dataservice.api.service.ColorService;

@RestController
public class ColorController {
	
	@Autowired
	ColorService colorService;

	@GetMapping(value = "/colors")
	List<ColorDTO> getAllColors() {
		return colorService.getAllColors();
	}

	@GetMapping(value = "/colors/{colorId}")
	ColorDTO getColorBycolorId(@PathVariable(name = "colorId") Integer colorId) {
		return colorService.getColorBycolorId(colorId);
	}

	@PostMapping(value = "/colors")
	ColorDTO createColor(@RequestBody ColorDTO colorDto) {
		return colorService.createColor(colorDto);
	}

	@PutMapping(value = "/colors/{colorId}")
	ColorDTO updateColor(@PathVariable(name = "colorId") Integer colorId, @RequestBody ColorDTO colorDto) {
		return colorService.updateColor(colorId, colorDto);
	}

	@DeleteMapping(value = "/colors/{colorId}")
	ColorDTO deleteColor(@PathVariable(name = "colorId") Integer colorId) {
		return colorService.deleteColor(colorId);
	}
}
