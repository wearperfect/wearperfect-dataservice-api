package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.CategoryBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.ColorBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.FiltersResponseDTO;
import com.wearperfect.dataservice.api.dto.GenderCategoryBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.RegionBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.StyleBasicDetailsDTO;
import com.wearperfect.dataservice.api.entities.Category_;
import com.wearperfect.dataservice.api.entities.Color_;
import com.wearperfect.dataservice.api.entities.GenderCategory_;
import com.wearperfect.dataservice.api.entities.Region_;
import com.wearperfect.dataservice.api.mappers.CategoryMapper;
import com.wearperfect.dataservice.api.mappers.ColorMapper;
import com.wearperfect.dataservice.api.mappers.GenderCategoryMapper;
import com.wearperfect.dataservice.api.mappers.PreferenceFilterMapper;
import com.wearperfect.dataservice.api.mappers.RegionMapper;
import com.wearperfect.dataservice.api.mappers.StyleMapper;
import com.wearperfect.dataservice.api.repository.CategoryRepository;
import com.wearperfect.dataservice.api.repository.ColorRepository;
import com.wearperfect.dataservice.api.repository.GenderCategoryRepository;
import com.wearperfect.dataservice.api.repository.RegionRepository;
import com.wearperfect.dataservice.api.repository.StyleRepository;
import com.wearperfect.dataservice.api.service.FilterService;
import com.wearperfect.dataservice.api.service.StyleService;

@Service
@Transactional
public class FilterServiceImpl implements FilterService {

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

}
