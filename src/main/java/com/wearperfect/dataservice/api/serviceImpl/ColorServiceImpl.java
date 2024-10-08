package com.wearperfect.dataservice.api.serviceImpl;

import com.wearperfect.dataservice.api.dto.ColorDTO;
import com.wearperfect.dataservice.api.entity.Color;
import com.wearperfect.dataservice.api.entity.Color_;
import com.wearperfect.dataservice.api.mapper.ColorMapper;
import com.wearperfect.dataservice.api.repository.ColorRepository;
import com.wearperfect.dataservice.api.service.ColorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ColorServiceImpl implements ColorService{
	
	@Autowired
	ColorRepository colorRepository;
	
	@Autowired
	ColorMapper colorMapper;

	@Override
	public List<ColorDTO> getAllColors() {
		List<Color> colors = colorRepository.findAll(Sort.by(Direction.ASC, Color_.NAME));
		return colors.stream().map(color->colorMapper.mapColorToColorDto(color)).collect(Collectors.toList());
	}

	@Override
	public ColorDTO getColorBycolorId(Integer colorId) {
		Optional<Color> color = colorRepository.findById(colorId);
		if(color.isPresent()) {
			return colorMapper.mapColorToColorDto(color.get());
		}else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ColorDTO createColor(ColorDTO colorDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ColorDTO updateColor(Integer colorId, ColorDTO colorDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ColorDTO deleteColor(Integer colorId) {
		// TODO Auto-generated method stub
		return null;
	}

}
