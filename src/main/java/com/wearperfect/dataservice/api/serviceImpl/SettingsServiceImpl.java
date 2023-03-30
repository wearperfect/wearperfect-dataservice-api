package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.dto.FeatureBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.RoleDetailsDTO;
import com.wearperfect.dataservice.api.entity.Feature;
import com.wearperfect.dataservice.api.entity.Role;
import com.wearperfect.dataservice.api.mapper.FeatureMapper;
import com.wearperfect.dataservice.api.mapper.RoleMapper;
import com.wearperfect.dataservice.api.repository.FeatureRepository;
import com.wearperfect.dataservice.api.repository.RoleRepository;
import com.wearperfect.dataservice.api.service.SettingsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SettingsServiceImpl implements SettingsService{
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	FeatureRepository featureRepository;
	
	@Autowired
	FeatureMapper featureMapper;

	@Override
	public List<RoleDetailsDTO> getSwitchableAccountTypes() {
		
		List<Role> roles = roleRepository.findAll();
		return roles.stream().map(role->roleMapper.mapRoleToRoleDetailsDto(role)).collect(Collectors.toList());
	}

	@Override
	public List<FeatureBasicDetailsDTO> getFeatures() {
		List<Feature> features = featureRepository.findAll();
		return features.stream().map(feature->featureMapper.mapFeatureToFeatureBasicDetailsDto(feature)).collect(Collectors.toList());
	}

}
