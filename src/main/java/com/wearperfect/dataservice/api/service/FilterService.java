package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.FiltersResponseDTO;
import com.wearperfect.dataservice.api.dto.SavedFilterDTO;
import com.wearperfect.dataservice.api.dto.SavedFilterDetailsDTO;

public interface FilterService {

	FiltersResponseDTO getFilters();

	List<SavedFilterDetailsDTO> getUserSavedFilters(Long userId);

	SavedFilterDetailsDTO addUserSavedFilters(Long userId, SavedFilterDTO savedFilterDto);

	SavedFilterDTO updateUserPreferenceFilter(Long userId, Long filterId, SavedFilterDTO savedFilterDto);

	SavedFilterDTO deleteUserPreferenceFilter(Long userId, Long filterId);

	SavedFilterDetailsDTO getUserSavedFilterByIdAndUserId(Long id, Long userId);

}
