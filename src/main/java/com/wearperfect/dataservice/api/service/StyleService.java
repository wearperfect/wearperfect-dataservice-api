package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.StyleBasicDetailsDTO;

public interface StyleService {

	List<StyleBasicDetailsDTO> getStyles();

	List<StyleBasicDetailsDTO> getUserStyles(Long userId);

	StyleBasicDetailsDTO saveUserStyle(Long userId, Integer styleId);

	StyleBasicDetailsDTO deleteUserStyle(Long userId, Integer styleId);

}
