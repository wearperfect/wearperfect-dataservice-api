package com.wearperfect.dataservice.api.controller;

import com.wearperfect.dataservice.api.dto.RegionDTO;
import com.wearperfect.dataservice.api.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegionController {

    @Autowired
    RegionService regionService;

    @GetMapping(value = "/v1/regions")
    List<RegionDTO> getAllRegions() {
        return regionService.getAllRegions();
    }

    @GetMapping(value = "/v1/regions/{regionId}")
    RegionDTO getRegionByRegionId(@PathVariable(name = "regionId") Integer regionId) {
        return regionService.getRegionByRegionId(regionId);
    }

    @PostMapping(value = "/v1/regions")
    RegionDTO createRegion(@RequestBody RegionDTO regionId) {
        return regionService.createRegion(regionId);
    }

    @PutMapping(value = "/v1/regions/{regionId}")
    RegionDTO updateRegion(@PathVariable(name = "regionId") Integer regionId, @RequestBody RegionDTO regionDto) {
        return regionService.updateRegion(regionId, regionDto);
    }

    @DeleteMapping(value = "/v1/regions/{regionId}")
    RegionDTO deleteRegion(@PathVariable(name = "regionId") Integer regionId) {
        return regionService.deleteRegion(regionId);
    }

}
