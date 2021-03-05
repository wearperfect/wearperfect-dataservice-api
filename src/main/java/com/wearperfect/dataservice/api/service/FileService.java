package com.wearperfect.dataservice.api.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	File converMultipartFileToFile(MultipartFile file);
	
	String getFileExtension(String fileName);

	BufferedImage convertToBufferedImage(Image image);

	BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException;
	
	BufferedImage resizeImageByPercent(BufferedImage originalImage, double scale) throws IOException;
}
