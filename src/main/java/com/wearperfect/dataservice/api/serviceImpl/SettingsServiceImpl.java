package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.FeatureBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.RoleDetailsDTO;
import com.wearperfect.dataservice.api.entities.Feature;
import com.wearperfect.dataservice.api.entities.Role;
import com.wearperfect.dataservice.api.mappers.FeatureMapper;
import com.wearperfect.dataservice.api.mappers.RoleMapper;
import com.wearperfect.dataservice.api.repository.FeatureRepository;
import com.wearperfect.dataservice.api.repository.RoleRepository;
import com.wearperfect.dataservice.api.service.SettingsService;

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
