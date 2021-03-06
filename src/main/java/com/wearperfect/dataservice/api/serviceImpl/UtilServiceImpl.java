package com.wearperfect.dataservice.api.serviceImpl;

import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

import com.wearperfect.dataservice.api.service.UtilService;

@Service
public class UtilServiceImpl implements UtilService{

	@Override
	public float getRatio(int width, int height) {
		float x = (float) width;
		float y = (float) height;
		float ratio = y/x * 100;
		DecimalFormat df = new DecimalFormat("#.##");
		return Float.valueOf(df.format(ratio));
	}
}
