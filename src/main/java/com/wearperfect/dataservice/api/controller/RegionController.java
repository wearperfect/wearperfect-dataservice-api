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

import com.wearperfect.dataservice.api.dto.RegionDTO;
import com.wearperfect.dataservice.api.service.RegionService;

@RestController
public class RegionController {
	
	@Autowired
	RegionService regionService;

	@GetMapping(value = "/regions")
	List<RegionDTO> getAllRegions() {
		return regionService.getAllRegions();
	}

	@GetMapping(value = "/regions/{regionId}")
	RegionDTO getRegionByRegionId(@PathVariable(name = "regionId") Integer regionId) {
		return regionService.getRegionByRegionId(regionId);
	}

	@PostMapping(value = "/regions")
	RegionDTO createRegion(@RequestBody RegionDTO regionId) {
		return regionService.createRegion(regionId);
	}

	@PutMapping(value = "/regions/{regionId}")
	RegionDTO updateRegion(@PathVariable(name = "regionId") Integer regionId, @RequestBody RegionDTO regionDto) {
		return regionService.updateRegion(regionId, regionDto);
	}

	@DeleteMapping(value = "/regions/{regionId}")
	RegionDTO deleteRegion(@PathVariable(name = "regionId") Integer regionId) {
		return regionService.deleteRegion(regionId);
	}

}
