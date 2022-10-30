package com.wearperfect.dataservice.api.serviceImpl;

import java.util.ArrayList;
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

import com.wearperfect.dataservice.api.dto.StyleBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserStylesResponseDTO;
import com.wearperfect.dataservice.api.entities.Style;
import com.wearperfect.dataservice.api.entities.Style_;
import com.wearperfect.dataservice.api.entities.UserStyle;
import com.wearperfect.dataservice.api.mappers.StyleMapper;
import com.wearperfect.dataservice.api.repository.StyleRepository;
import com.wearperfect.dataservice.api.repository.UserStyleRepository;
import com.wearperfect.dataservice.api.service.StyleService;

@Service
@Transactional
public class StyleServiceImpl implements StyleService {

	@Autowired
	StyleRepository styleRepository;

	@Autowired
	UserStyleRepository userStyleRepository;

	@Autowired
	StyleMapper styleMapper;

	@Override
	public List<StyleBasicDetailsDTO> getStyles() {
		List<Style> styles = styleRepository.findAll(Sort.by(Direction.ASC, Style_.NAME));
		return styles.stream().map(style -> styleMapper.mapStyleToStyleBasicDetailsDto(style))
				.collect(Collectors.toList());
	}

	@Override
	public UserStylesResponseDTO getUserStyles(Long userId) {
		List<UserStyle> userStyles = userStyleRepository.findByUserId(userId);
		List<Integer> userStyleIds = userStyles.stream().map(userStyle -> userStyle.getStyleId())
				.collect(Collectors.toList());
		List<Style> styles = styleRepository.findByIdIn(userStyleIds, Sort.by(Direction.ASC, Style_.NAME));
		List<StyleBasicDetailsDTO> styleDtoList = styles.stream().map(style -> styleMapper.mapStyleToStyleBasicDetailsDto(style))
				.collect(Collectors.toList());
		return new UserStylesResponseDTO(userId, styleDtoList);
	}

	@Override
	public UserStylesResponseDTO saveUserStyle(Long userId, Integer styleId) {
		UserStyle userStyle = new UserStyle();
		userStyle.setActive(true);
		userStyle.setCreatedBy(userId);
		userStyle.setCreatedOn(new Date());
		userStyle.setStyleId(styleId);
		userStyle.setUserId(userId);
		userStyleRepository.save(userStyle);
		Optional<Style> style = styleRepository.findById(styleId);
		return getUserSkillsResponseDTO(userId, styleId, style);
	}

	@Override
	public UserStylesResponseDTO deleteUserStyle(Long userId, Integer styleId) {
		userStyleRepository.deleteByUserIdAndStyleId(userId, styleId);
		Optional<Style> style = styleRepository.findById(styleId);
		return getUserSkillsResponseDTO(userId, styleId, style);
	}
	
	UserStylesResponseDTO getUserSkillsResponseDTO(Long userId, Integer styleId, Optional<Style> style) {
		List<StyleBasicDetailsDTO> stylesDtoList = new ArrayList<>();
		if(style.isPresent()) {
			stylesDtoList.add(styleMapper.mapStyleToStyleBasicDetailsDto(style.get()));
			return new UserStylesResponseDTO(userId, stylesDtoList);
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Style not found with id "+styleId);
		}
	}

}
