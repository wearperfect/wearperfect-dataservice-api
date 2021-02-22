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
import com.wearperfect.dataservice.api.dto.PreferenceFilterColorDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterDetailsDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterGenderCategoryDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterRegionDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterStyleDTO;
import com.wearperfect.dataservice.api.dto.PreferenceFilterUserDTO;
import com.wearperfect.dataservice.api.entities.Category;
import com.wearperfect.dataservice.api.entities.Color;
import com.wearperfect.dataservice.api.entities.GenderCategory;
import com.wearperfect.dataservice.api.entities.PreferenceFilter;
import com.wearperfect.dataservice.api.entities.PreferenceFilterCategory;
import com.wearperfect.dataservice.api.entities.PreferenceFilterColor;
import com.wearperfect.dataservice.api.entities.PreferenceFilterGenderCategory;
import com.wearperfect.dataservice.api.entities.PreferenceFilterRegion;
import com.wearperfect.dataservice.api.entities.PreferenceFilterStyle;
import com.wearperfect.dataservice.api.entities.Region;
import com.wearperfect.dataservice.api.entities.Style;
import com.wearperfect.dataservice.api.mappers.CategoryMapper;
import com.wearperfect.dataservice.api.mappers.ColorMapper;
import com.wearperfect.dataservice.api.mappers.GenderCategoryMapper;
import com.wearperfect.dataservice.api.mappers.PreferenceFilterCategoryMapper;
import com.wearperfect.dataservice.api.mappers.PreferenceFilterColorMapper;
import com.wearperfect.dataservice.api.mappers.PreferenceFilterGenderCategoryMapper;
import com.wearperfect.dataservice.api.mappers.PreferenceFilterMapper;
import com.wearperfect.dataservice.api.mappers.PreferenceFilterRegionMapper;
import com.wearperfect.dataservice.api.mappers.PreferenceFilterStyleMapper;
import com.wearperfect.dataservice.api.mappers.PreferenceFilterUserMapper;
import com.wearperfect.dataservice.api.mappers.RegionMapper;
import com.wearperfect.dataservice.api.mappers.StyleMapper;
import com.wearperfect.dataservice.api.repositories.CategoryRepository;
import com.wearperfect.dataservice.api.repositories.ColorRepository;
import com.wearperfect.dataservice.api.repositories.GenderCategoryRepository;
import com.wearperfect.dataservice.api.repositories.PreferenceFilterCategoryRepository;
import com.wearperfect.dataservice.api.repositories.PreferenceFilterColorRepository;
import com.wearperfect.dataservice.api.repositories.PreferenceFilterGenderCategoryRepository;
import com.wearperfect.dataservice.api.repositories.PreferenceFilterRegionRepository;
import com.wearperfect.dataservice.api.repositories.PreferenceFilterRepository;
import com.wearperfect.dataservice.api.repositories.PreferenceFilterStyleRepository;
import com.wearperfect.dataservice.api.repositories.PreferenceFilterUserRepository;
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

	@Autowired
	PreferenceFilterCategoryRepository preferenceFilterCategoryRepository;

	@Autowired
	PreferenceFilterCategoryMapper preferenceFilterCategoryMapper;

	@Autowired
	PreferenceFilterColorRepository preferenceFilterColorRepository;

	@Autowired
	PreferenceFilterColorMapper preferenceFilterColorMapper;

	@Autowired
	PreferenceFilterGenderCategoryRepository preferenceFilterGenderCategoryRepository;

	@Autowired
	PreferenceFilterGenderCategoryMapper preferenceFilterGenderCategoryMapper;

	@Autowired
	PreferenceFilterRegionRepository preferenceFilterRegionRepository;

	@Autowired
	PreferenceFilterRegionMapper preferenceFilterRegionMapper;

	@Autowired
	PreferenceFilterStyleRepository preferenceFilterStyleRepository;

	@Autowired
	PreferenceFilterStyleMapper preferenceFilterStyleMapper;

	@Autowired
	PreferenceFilterUserRepository preferenceFilterUserRepository;

	@Autowired
	PreferenceFilterUserMapper preferenceFilterUserMapper;

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
	public PreferenceFilterDTO updateUserPreferenceFilter(Long userId, Long filterId,
			PreferenceFilterDTO savedFilterDto) {
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
	public PreferenceFilterCategoryDTO addCategoryToPreferenceFilter(Long userId, Long filterId, Integer categoryId) {
		PreferenceFilterCategory preferenceFilterCategory = new PreferenceFilterCategory();
		preferenceFilterCategory.setActive(true);
		preferenceFilterCategory.setCategoryId(categoryId);
		preferenceFilterCategory.setCreatedOn(new Date());
		preferenceFilterCategory.setPreferenceFilterId(filterId);
		preferenceFilterCategoryRepository.save(preferenceFilterCategory);
		Optional<Category> category = categoryRepository.findById(preferenceFilterCategory.getCategoryId());
		preferenceFilterCategory.setCategory(category.get());
		return preferenceFilterCategoryMapper
				.mapPreferenceFilterCategoryToPreferenceFilterCategoryDto(preferenceFilterCategory);
	}

	@Override
	public PreferenceFilterCategoryDTO deleteCategoryFromPreferenceFilter(Long userId, Long filterId,
			Integer categoryId) {
		Optional<PreferenceFilterCategory> preferenceFilterCategory = preferenceFilterCategoryRepository
				.findByPreferenceFilterIdAndCategoryId(filterId, categoryId);
		if (preferenceFilterCategory.isPresent()) {
			preferenceFilterCategoryRepository.deleteById(preferenceFilterCategory.get().getId());
			return preferenceFilterCategoryMapper
					.mapPreferenceFilterCategoryToPreferenceFilterCategoryDto(preferenceFilterCategory.get());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public PreferenceFilterColorDTO addColorToPreferenceFilter(Long userId, Long filterId, Integer colorId) {
		PreferenceFilterColor preferenceFilterColor = new PreferenceFilterColor();
		preferenceFilterColor.setActive(true);
		preferenceFilterColor.setColorId(colorId);
		preferenceFilterColor.setCreatedOn(new Date());
		preferenceFilterColor.setPreferenceFilterId(filterId);
		preferenceFilterColorRepository.save(preferenceFilterColor);
		Optional<Color> color = colorRepository.findById(preferenceFilterColor.getColorId());
		preferenceFilterColor.setColor(color.get());
		return preferenceFilterColorMapper.mapPreferenceFilterColorToPreferenceFilterColorDto(preferenceFilterColor);
	}

	@Override
	public PreferenceFilterColorDTO deleteColorFromPreferenceFilter(Long userId, Long filterId, Integer colorId) {
		Optional<PreferenceFilterColor> preferenceFilterColor = preferenceFilterColorRepository
				.findByPreferenceFilterIdAndColorId(filterId, colorId);
		if (preferenceFilterColor.isPresent()) {
			preferenceFilterColorRepository.deleteById(preferenceFilterColor.get().getId());
			return preferenceFilterColorMapper
					.mapPreferenceFilterColorToPreferenceFilterColorDto(preferenceFilterColor.get());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public PreferenceFilterGenderCategoryDTO addGenderCategoryToPreferenceFilter(Long userId, Long filterId,
			Integer genderCategoryId) {
		PreferenceFilterGenderCategory preferenceFilterGenderCategory = new PreferenceFilterGenderCategory();
		preferenceFilterGenderCategory.setActive(true);
		preferenceFilterGenderCategory.setCreatedOn(new Date());
		preferenceFilterGenderCategory.setGenderCategoryId(genderCategoryId);
		preferenceFilterGenderCategory.setPreferenceFilterId(filterId);
		preferenceFilterGenderCategoryRepository.save(preferenceFilterGenderCategory);
		Optional<GenderCategory> genderCategory = genderCategoryRepository.findById(preferenceFilterGenderCategory.getGenderCategoryId());
		preferenceFilterGenderCategory.setGenderCategory(genderCategory.get());
		return preferenceFilterGenderCategoryMapper
				.mapPreferenceFilterGenderCategoryToPreferenceFilterGenderCategoryDto(preferenceFilterGenderCategory);
	}

	@Override
	public PreferenceFilterGenderCategoryDTO deleteGenderCategoryFromPreferenceFilter(Long userId, Long filterId,
			Integer genderCategoryId) {
		Optional<PreferenceFilterGenderCategory> preferenceFilterGenderCategory = preferenceFilterGenderCategoryRepository
				.findByPreferenceFilterIdAndGenderCategoryId(filterId, genderCategoryId);
		if (preferenceFilterGenderCategory.isPresent()) {
			preferenceFilterGenderCategoryRepository.deleteById(preferenceFilterGenderCategory.get().getId());
			return preferenceFilterGenderCategoryMapper
					.mapPreferenceFilterGenderCategoryToPreferenceFilterGenderCategoryDto(
							preferenceFilterGenderCategory.get());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public PreferenceFilterRegionDTO addRegionToPreferenceFilter(Long userId, Long filterId, Integer regionId) {
		PreferenceFilterRegion preferenceFilterRegion = new PreferenceFilterRegion();
		preferenceFilterRegion.setActive(true);
		preferenceFilterRegion.setCreatedOn(new Date());
		preferenceFilterRegion.setRegionId(regionId);
		preferenceFilterRegion.setPreferenceFilterId(filterId);
		preferenceFilterRegionRepository.save(preferenceFilterRegion);
		Optional<Region> region = regionRepository.findById(preferenceFilterRegion.getRegionId());
		preferenceFilterRegion.setRegion(region.get());
		return preferenceFilterRegionMapper
				.mapPreferenceFilterRegionToPreferenceFilterRegionDto(preferenceFilterRegion);
	}

	@Override
	public PreferenceFilterRegionDTO deleteRegionFromPreferenceFilter(Long userId, Long filterId, Integer regionId) {
		Optional<PreferenceFilterRegion> preferenceFilterRegion = preferenceFilterRegionRepository
				.findByPreferenceFilterIdAndRegionId(filterId, regionId);
		if (preferenceFilterRegion.isPresent()) {
			preferenceFilterRegionRepository.deleteById(preferenceFilterRegion.get().getId());
			return preferenceFilterRegionMapper
					.mapPreferenceFilterRegionToPreferenceFilterRegionDto(preferenceFilterRegion.get());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public PreferenceFilterStyleDTO addStyleToPreferenceFilter(Long userId, Long filterId, Integer styleId) {
		PreferenceFilterStyle preferenceFilterStyle = new PreferenceFilterStyle();
		preferenceFilterStyle.setActive(true);
		preferenceFilterStyle.setCreatedOn(new Date());
		preferenceFilterStyle.setStyleId(styleId);
		preferenceFilterStyle.setPreferenceFilterId(filterId);
		preferenceFilterStyleRepository.save(preferenceFilterStyle);
		Optional<Style> style = styleRepository.findById(preferenceFilterStyle.getStyleId());
		preferenceFilterStyle.setStyle(style.get());
		return preferenceFilterStyleMapper.mapPreferenceFilterStyleToPreferenceFilterStyleDto(preferenceFilterStyle);
	}

	@Override
	public PreferenceFilterStyleDTO deleteStyleFromPreferenceFilter(Long userId, Long filterId, Integer styleId) {
		Optional<PreferenceFilterStyle> preferenceFilterStyle = preferenceFilterStyleRepository
				.findByPreferenceFilterIdAndStyleId(filterId, styleId);
		if (preferenceFilterStyle.isPresent()) {
			preferenceFilterStyleRepository.deleteById(preferenceFilterStyle.get().getId());
			return preferenceFilterStyleMapper
					.mapPreferenceFilterStyleToPreferenceFilterStyleDto(preferenceFilterStyle.get());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public PreferenceFilterUserDTO addUserToPreferenceFilter(Long userId, Long filterId, Long preferredUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreferenceFilterUserDTO deleteUserFromPreferenceFilter(Long userId, Long filterId, Long preferredUserId) {
		// TODO Auto-generated method stub
		return null;
	}
}
