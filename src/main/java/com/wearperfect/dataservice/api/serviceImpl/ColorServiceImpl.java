package com.wearperfect.dataservice.api.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.wearperfect.dataservice.api.dto.ColorDTO;
import com.wearperfect.dataservice.api.entities.Color;
import com.wearperfect.dataservice.api.mappers.ColorMapper;
import com.wearperfect.dataservice.api.repositories.ColorRepository;
import com.wearperfect.dataservice.api.service.ColorService;

@Service
@Transactional
public class ColorServiceImpl implements ColorService{
	
	@Autowired
	ColorRepository colorRepository;
	
	@Autowired
	ColorMapper colorMapper;

	@Override
	public List<ColorDTO> getAllColors() {
		List<Color> colors = colorRepository.findAll();
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
