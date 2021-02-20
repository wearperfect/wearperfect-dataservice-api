package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.dto.PreferenceFilterCategoryDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterDetailsDTO;
import com.wearperfect.dataservice.api.entities.PreferenceFilter;
import com.wearperfect.dataservice.api.mappers.CategoryMapper;
import com.wearperfect.dataservice.api.mappers.ColorMapper;
import com.wearperfect.dataservice.api.mappers.GenderCategoryMapper;
import com.wearperfect.dataservice.api.mappers.PreferenceFilterMapper;
import com.wearperfect.dataservice.api.mappers.RegionMapper;
import com.wearperfect.dataservice.api.mappers.StyleMapper;
import com.wearperfect.dataservice.api.repositories.CategoryRepository;
import com.wearperfect.dataservice.api.repositories.ColorRepository;
import com.wearperfect.dataservice.api.repositories.GenderCategoryRepository;
import com.wearperfect.dataservice.api.repositories.PreferenceFilterRepository;
import com.wearperfect.dataservice.api.repositories.RegionRepository;
import com.wearperfect.dataservice.api.repositories.StyleRepository;
import com.wearperfect.dataservice.api.service.PreferenceFilterService;
import com.wearperfect.dataservice.api.service.StyleService;

@Service
@Transactional
public class PreferenceFilterServiceImpl implements PreferenceFilterService {
	@Autowired
	PreferenceFilterRepository preferenceFilterRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ColorRepository colorRepository;

	@Autowired
	GenderCategoryRepository genderCategoryRepository;

	@Autowired
	RegionRepository regionRepository;

	@Autowired
	StyleRepository styleRepository;

	@Autowired
	PreferenceFilterMapper preferenceFilterMapper;

	@Autowired
	CategoryMapper categoryMapper;

	@Autowired
	ColorMapper colorMapper;

	@Autowired
	GenderCategoryMapper genderCategoryMapper;

	@Autowired
	RegionMapper regionMapper;

	@Autowired
	StyleMapper styleMapper;

	@Autowired
	StyleService styleService;

	@Override
	public List<PreferenceFilterDetailsDTO> getUserSavedFilters(Long userId) {
		List<PreferenceFilter> preferenceFilters = preferenceFilterRepository.findByUserId(userId);
		List<PreferenceFilterDetailsDTO> savedFilterDetailsDtoList = preferenceFilters.stream()
				.map(savedFilter -> preferenceFilterMapper.mapSavedFilterToSavedFilterDetailsDto(savedFilter))
				.collect(Collectors.toList());
		return savedFilterDetailsDtoList;
	}

	@Override
	public PreferenceFilterDetailsDTO getUserSavedFilterByIdAndUserId(Long id, Long userId) {
		PreferenceFilter preferenceFilter = preferenceFilterRepository.findByIdAndUserId(id, userId);
		return preferenceFilterMapper.mapSavedFilterToSavedFilterDetailsDto(preferenceFilter);
	}

	@Override
	public PreferenceFilterDetailsDTO addUserSavedFilters(Long userId, PreferenceFilterDTO savedFilterDto) {
		if (userId != savedFilterDto.getUserId()) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		if (null == savedFilterDto.getTitle() || savedFilterDto.getTitle().trim().length() <= 0
				|| null == savedFilterDto.getDescription() || savedFilterDto.getDescription().trim().length() <= 0) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		PreferenceFilter filter = preferenceFilterMapper.mapSavedFilterDtoToSavedFilter(savedFilterDto);
		filter.setActive(true);
		filter.setCreatedBy(userId);
		filter.setCreatedOn(new Date());
		filter.setUserId(userId);
		preferenceFilterRepository.save(filter);
		// return getUserSavedFilterByIdAndUserId(filter.getId(), filter.getUserId());
		return preferenceFilterMapper.mapSavedFilterToSavedFilterDetailsDto(filter);
	}

	@Override
	public PreferenceFilterDTO updateUserPreferenceFilter(Long userId, Long filterId, PreferenceFilterDTO savedFilterDto) {
		if (null == savedFilterDto.getId() || null == savedFilterDto.getTitle()
				|| savedFilterDto.getTitle().trim().length() <= 0 || null == savedFilterDto.getDescription()
				|| savedFilterDto.getDescription().trim().length() <= 0) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		Optional<PreferenceFilter> preferenceFilter = preferenceFilterRepository.findById(filterId);
		if (preferenceFilter.isPresent()) {
			preferenceFilter.get().setTitle(savedFilterDto.getTitle());
			preferenceFilter.get().setDescription(savedFilterDto.getDescription());
			preferenceFilterRepository.save(preferenceFilter.get());
			return preferenceFilterMapper.mapSavedFilterToSavedFilterDto(preferenceFilter.get());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public PreferenceFilterDTO deleteUserPreferenceFilter(Long userId, Long filterId) {
		Optional<PreferenceFilter> preferenceFilter = preferenceFilterRepository.findById(filterId);
		if (preferenceFilter.isPresent()) {
			preferenceFilterRepository.deleteById(filterId);
			return preferenceFilterMapper.mapSavedFilterToSavedFilterDto(preferenceFilter.get());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public PreferenceFilterCategoryDTO addCategoryToPreferenceFilter(Long userId, Long filterId, Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreferenceFilterCategoryDTO deleteCategoryFromPreferenceFilter(Long userId, Long filterId, Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreferenceFilterCategoryDTO addColorToPreferenceFilter(Long userId, Long filterId, Long colorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreferenceFilterCategoryDTO deleteColorFromPreferenceFilter(Long userId, Long filterId, Long colorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreferenceFilterCategoryDTO addGenderCategoryToPreferenceFilter(Long userId, Long filterId,
			Long genderCategoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreferenceFilterCategoryDTO deleteGenderCategoryFromPreferenceFilter(Long userId, Long filterId,
			Long genderCategoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreferenceFilterCategoryDTO addRegionToPreferenceFilter(Long userId, Long filterId, Long regionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreferenceFilterCategoryDTO deleteRegionFromPreferenceFilter(Long userId, Long filterId, Long regionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreferenceFilterCategoryDTO addStyleToPreferenceFilter(Long userId, Long filterId, Long styleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreferenceFilterCategoryDTO deleteStyleFromPreferenceFilter(Long userId, Long filterId, Long styleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreferenceFilterCategoryDTO addUserToPreferenceFilter(Long userId, Long filterId, Long preferredUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreferenceFilterCategoryDTO deleteUserFromPreferenceFilter(Long userId, Long filterId,
			Long preferredUserId) {
		// TODO Auto-generated method stub
		return null;
	}
}
