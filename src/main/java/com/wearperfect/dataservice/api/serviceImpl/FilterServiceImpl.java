package com.wearperfect.dataservice.api.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.dto.CategoryBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ColorBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.FiltersResponseDTO;
import com.wearperfect.dataservice.api.dto.GenderCategoryBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.RegionBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.SavedFilterDTO;
import com.wearperfect.dataservice.api.dto.SavedFilterDetailsDTO;
import com.wearperfect.dataservice.api.dto.StyleBasicDetailsDTO;
import com.wearperfect.dataservice.api.entities.Category_;
import com.wearperfect.dataservice.api.entities.Color_;
import com.wearperfect.dataservice.api.entities.GenderCategory_;
import com.wearperfect.dataservice.api.entities.Region_;
import com.wearperfect.dataservice.api.entities.SavedFilter;
import com.wearperfect.dataservice.api.mappers.CategoryMapper;
import com.wearperfect.dataservice.api.mappers.ColorMapper;
import com.wearperfect.dataservice.api.mappers.GenderCategoryMapper;
import com.wearperfect.dataservice.api.mappers.RegionMapper;
import com.wearperfect.dataservice.api.mappers.SavedFilterMapper;
import com.wearperfect.dataservice.api.mappers.StyleMapper;
import com.wearperfect.dataservice.api.repositories.CategoryRepository;
import com.wearperfect.dataservice.api.repositories.ColorRepository;
import com.wearperfect.dataservice.api.repositories.GenderCategoryRepository;
import com.wearperfect.dataservice.api.repositories.RegionRepository;
import com.wearperfect.dataservice.api.repositories.SavedFilterRepository;
import com.wearperfect.dataservice.api.repositories.StyleRepository;
import com.wearperfect.dataservice.api.service.FilterService;
import com.wearperfect.dataservice.api.service.StyleService;

@Service
@Transactional
public class FilterServiceImpl implements FilterService {

	@Autowired
	SavedFilterRepository savedFilterRepository;

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
	SavedFilterMapper savedFilterMapper;

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
	public FiltersResponseDTO getFilters() {

		List<CategoryBasicDetailsDTO> categories = categoryRepository.findAll(Sort.by(Direction.ASC, Category_.NAME))
				.stream().map(category -> categoryMapper.mapCategoryToCategoryBasicDetailsDTO(category))
				.collect(Collectors.toList());

		List<ColorBasicDetailsDTO> colors = colorRepository.findAll(Sort.by(Direction.ASC, Color_.NAME)).stream()
				.map(color -> colorMapper.mapColorToColorBasicDetailsDto(color)).collect(Collectors.toList());

		List<GenderCategoryBasicDetailsDTO> genderCategories = genderCategoryRepository
				.findAll(Sort.by(Direction.ASC, GenderCategory_.ID)).stream().map(genderCategory -> genderCategoryMapper
						.mapGenderCategoryToGenderCategoryBasicDetailsDto(genderCategory))
				.collect(Collectors.toList());

		List<RegionBasicDetailsDTO> regions = regionRepository.findAll(Sort.by(Direction.ASC, Region_.NAME)).stream()
				.map(region -> regionMapper.mapRegionToRegionBasicDetailsDto(region)).collect(Collectors.toList());

		List<StyleBasicDetailsDTO> styles = styleService.getStyles();

		return new FiltersResponseDTO(categories, colors, genderCategories, regions, styles);
	}

	@Override
	public List<SavedFilterDetailsDTO> getUserSavedFilters(Long userId) {
		List<SavedFilter> savedFilters = savedFilterRepository.findByUserId(userId);
		List<SavedFilterDetailsDTO> savedFilterDetailsDtoList = savedFilters.stream()
				.map(savedFilter -> savedFilterMapper.mapSavedFilterToSavedFilterDetailsDto(savedFilter))
				.collect(Collectors.toList());
		return savedFilterDetailsDtoList;
	}
	
	@Override
	public SavedFilterDetailsDTO getUserSavedFilterByIdAndUserId(Long id, Long userId) {
		SavedFilter savedFilter = savedFilterRepository.findByIdAndUserId(id, userId);
		return savedFilterMapper.mapSavedFilterToSavedFilterDetailsDto(savedFilter);
	}

	@Override
	public SavedFilterDetailsDTO addUserSavedFilters(Long userId, SavedFilterDTO savedFilterDto) {
		if (userId != savedFilterDto.getUserId()) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		if (null == savedFilterDto.getTitle() || savedFilterDto.getTitle().trim().length() <= 0
				|| null == savedFilterDto.getDescription() || savedFilterDto.getDescription().trim().length() <= 0) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		SavedFilter filter = savedFilterMapper.mapSavedFilterDtoToSavedFilter(savedFilterDto);
		filter.setActive(true);
		filter.setCreatedBy(userId);
		filter.setCreatedOn(new Date());
		filter.setUserId(userId);
		savedFilterRepository.save(filter);
		//return getUserSavedFilterByIdAndUserId(filter.getId(), filter.getUserId());
		return savedFilterMapper.mapSavedFilterToSavedFilterDetailsDto(filter);
	}

	@Override
	public SavedFilterDTO updateUserPreferenceFilter(Long userId, Long filterId, SavedFilterDTO savedFilterDto) {
		if (null == savedFilterDto.getId() || null == savedFilterDto.getTitle()
				|| savedFilterDto.getTitle().trim().length() <= 0 || null == savedFilterDto.getDescription()
				|| savedFilterDto.getDescription().trim().length() <= 0) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		Optional<SavedFilter> savedFilter = savedFilterRepository.findById(filterId);
		if (savedFilter.isPresent()) {
			savedFilter.get().setTitle(savedFilterDto.getTitle());
			savedFilter.get().setDescription(savedFilterDto.getDescription());
			savedFilterRepository.save(savedFilter.get());
			return savedFilterMapper.mapSavedFilterToSavedFilterDto(savedFilter.get());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public SavedFilterDTO deleteUserPreferenceFilter(Long userId, Long filterId) {
		Optional<SavedFilter> savedFilter = savedFilterRepository.findById(filterId);
		if (savedFilter.isPresent()) {
			savedFilterRepository.deleteById(filterId);
			return savedFilterMapper.mapSavedFilterToSavedFilterDto(savedFilter.get());
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

}
