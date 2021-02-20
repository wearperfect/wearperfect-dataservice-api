package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.PreferenceFilterDetailsDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterGenderCategoryDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterRegionDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterStyleDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterUserDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterCategoryDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterColorDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterDTO;

public interface PreferenceFilterService {

	List<PreferenceFilterDetailsDTO> getUserSavedFilters(Long userId);

	PreferenceFilterDetailsDTO addUserSavedFilters(Long userId, PreferenceFilterDTO savedFilterDto);

	PreferenceFilterDTO updateUserPreferenceFilter(Long userId, Long filterId, PreferenceFilterDTO savedFilterDto);

	PreferenceFilterDTO deleteUserPreferenceFilter(Long userId, Long filterId);

	PreferenceFilterDetailsDTO getUserSavedFilterByIdAndUserId(Long id, Long userId);

	PreferenceFilterCategoryDTO addCategoryToPreferenceFilter(Long userId, Long filterId, Integer categoryId);

	PreferenceFilterCategoryDTO deleteCategoryFromPreferenceFilter(Long userId, Long filterId, Integer categoryId);

	PreferenceFilterColorDTO addColorToPreferenceFilter(Long userId, Long filterId, Integer colorId);

	PreferenceFilterColorDTO deleteColorFromPreferenceFilter(Long userId, Long filterId, Integer colorId);

	PreferenceFilterGenderCategoryDTO addGenderCategoryToPreferenceFilter(Long userId, Long filterId,
			Integer genderCategoryId);

	PreferenceFilterGenderCategoryDTO deleteGenderCategoryFromPreferenceFilter(Long userId, Long filterId,
			Integer genderCategoryId);

	PreferenceFilterRegionDTO addRegionToPreferenceFilter(Long userId, Long filterId, Integer regionId);

	PreferenceFilterRegionDTO deleteRegionFromPreferenceFilter(Long userId, Long filterId, Integer regionId);

	PreferenceFilterStyleDTO addStyleToPreferenceFilter(Long userId, Long filterId, Integer styleId);

	PreferenceFilterStyleDTO deleteStyleFromPreferenceFilter(Long userId, Long filterId, Integer styleId);

	PreferenceFilterUserDTO addUserToPreferenceFilter(Long userId, Long filterId, Long preferredUserId);

	PreferenceFilterUserDTO deleteUserFromPreferenceFilter(Long userId, Long filterId, Long preferredUserId);

}
