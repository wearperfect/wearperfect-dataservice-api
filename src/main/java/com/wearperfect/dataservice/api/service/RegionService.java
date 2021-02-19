package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.RegionDTO;

public interface RegionService {

	List<RegionDTO> getAllRegions();

	RegionDTO getRegionByRegionId(Integer regionId);

	RegionDTO createRegion(RegionDTO regionId);

	RegionDTO updateRegion(Integer regionId, RegionDTO regionDto);

	RegionDTO deleteRegion(Integer regionId);

}
