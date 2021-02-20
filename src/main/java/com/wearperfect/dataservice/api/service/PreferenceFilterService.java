package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.PreferenceFilterDetailsDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterCategoryDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterDTO;

public interface PreferenceFilterService {

	List<PreferenceFilterDetailsDTO> getUserSavedFilters(Long userId);

	PreferenceFilterDetailsDTO addUserSavedFilters(Long userId, PreferenceFilterDTO savedFilterDto);

	PreferenceFilterDTO updateUserPreferenceFilter(Long userId, Long filterId, PreferenceFilterDTO savedFilterDto);

	PreferenceFilterDTO deleteUserPreferenceFilter(Long userId, Long filterId);

	PreferenceFilterDetailsDTO getUserSavedFilterByIdAndUserId(Long id, Long userId);

	PreferenceFilterCategoryDTO addCategoryToPreferenceFilter(Long userId, Long filterId, Long categoryId);

	PreferenceFilterCategoryDTO deleteCategoryFromPreferenceFilter(Long userId, Long filterId, Long categoryId);

	PreferenceFilterCategoryDTO addColorToPreferenceFilter(Long userId, Long filterId, Long colorId);

	PreferenceFilterCategoryDTO deleteColorFromPreferenceFilter(Long userId, Long filterId, Long colorId);

	PreferenceFilterCategoryDTO addGenderCategoryToPreferenceFilter(Long userId, Long filterId, Long genderCategoryId);

	PreferenceFilterCategoryDTO deleteGenderCategoryFromPreferenceFilter(Long userId, Long filterId,
			Long genderCategoryId);

	PreferenceFilterCategoryDTO addRegionToPreferenceFilter(Long userId, Long filterId, Long regionId);

	PreferenceFilterCategoryDTO deleteRegionFromPreferenceFilter(Long userId, Long filterId, Long regionId);

	PreferenceFilterCategoryDTO addStyleToPreferenceFilter(Long userId, Long filterId, Long styleId);

	PreferenceFilterCategoryDTO deleteStyleFromPreferenceFilter(Long userId, Long filterId, Long styleId);

	PreferenceFilterCategoryDTO addUserToPreferenceFilter(Long userId, Long filterId, Long preferredUserId);

	PreferenceFilterCategoryDTO deleteUserFromPreferenceFilter(Long userId, Long filterId, Long preferredUserId);

}
