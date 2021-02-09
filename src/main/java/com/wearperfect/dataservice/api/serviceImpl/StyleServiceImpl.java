package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.dto.StyleBasicDetailsDTO;
import com.wearperfect.dataservice.api.entities.Style;
import com.wearperfect.dataservice.api.entities.Style_;
import com.wearperfect.dataservice.api.entities.UserStyle;
import com.wearperfect.dataservice.api.mappers.StyleMapper;
import com.wearperfect.dataservice.api.repositories.StyleRepository;
import com.wearperfect.dataservice.api.repositories.UserStyleRepository;
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
	public List<StyleBasicDetailsDTO> getUserStyles(Long userId) {
		List<UserStyle> userStyles = userStyleRepository.findByUserId(userId);
		List<Integer> userStyleIds = userStyles.stream().map(userStyle -> userStyle.getSkillId())
				.collect(Collectors.toList());
		List<Style> styles = styleRepository.findByIdIn(userStyleIds);
		return styles.stream().map(style -> styleMapper.mapStyleToStyleBasicDetailsDto(style))
				.collect(Collectors.toList());
	}

}
