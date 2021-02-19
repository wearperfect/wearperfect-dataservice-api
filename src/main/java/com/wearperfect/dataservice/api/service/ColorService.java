package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.ColorDTO;

public interface ColorService {

	List<ColorDTO> getAllColors();

	ColorDTO getColorBycolorId(Integer colorId);

	ColorDTO createColor(ColorDTO colorDto);

	ColorDTO updateColor(Integer colorId, ColorDTO colorDto);

	ColorDTO deleteColor(Integer colorId);

}
