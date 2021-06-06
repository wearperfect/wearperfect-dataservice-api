package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.FeatureBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.RoleDetailsDTO;

public interface SettingsService {

	List<RoleDetailsDTO> getSwitchableAccountTypes();
	
	List<FeatureBasicDetailsDTO> getFeatures();
}
