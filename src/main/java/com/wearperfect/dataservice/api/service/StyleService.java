package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.StyleBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserStylesResponseDTO;

public interface StyleService {

	List<StyleBasicDetailsDTO> getStyles();

	UserStylesResponseDTO getUserStyles(Long userId);

	UserStylesResponseDTO saveUserStyle(Long userId, Integer styleId);

	UserStylesResponseDTO deleteUserStyle(Long userId, Integer styleId);
}
