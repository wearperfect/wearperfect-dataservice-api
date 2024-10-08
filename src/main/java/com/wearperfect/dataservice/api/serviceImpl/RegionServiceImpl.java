package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.dto.RegionDTO;
import com.wearperfect.dataservice.api.entity.Region;
import com.wearperfect.dataservice.api.entity.Region_;
import com.wearperfect.dataservice.api.mapper.RegionMapper;
import com.wearperfect.dataservice.api.repository.RegionRepository;
import com.wearperfect.dataservice.api.service.RegionService;

@Service
@Transactional
public class RegionServiceImpl implements RegionService{
	
	@Autowired
	RegionRepository regionRepository;
	
	@Autowired
	RegionMapper regionMapper;

	@Override
	public List<RegionDTO> getAllRegions() {
		List<Region> regions = regionRepository.findAll(Sort.by(Direction.ASC, Region_.NAME));
		return regions.stream().map(region->regionMapper.mapRegionToRegionDto(region)).collect(Collectors.toList());
	}

	@Override
	public RegionDTO getRegionByRegionId(Integer regionId) {
		Optional<Region> region = regionRepository.findById(regionId);
		if(region.isPresent()) {
			return regionMapper.mapRegionToRegionDto(region.get());
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public RegionDTO createRegion(RegionDTO regionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegionDTO updateRegion(Integer regionId, RegionDTO regionDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegionDTO deleteRegion(Integer regionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
