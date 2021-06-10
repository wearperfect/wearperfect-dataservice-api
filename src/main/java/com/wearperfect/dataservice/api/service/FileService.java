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
	
	File resizeImage(File file, String fileName, int targetWidth, int targetHeight) throws IOException;
	
	BufferedImage resizeImageByPercent(BufferedImage originalImage, double scale) throws IOException;

	File resizeImageByPercent(File file, String fileName, double scale) throws IOException;
	
	Float getFileAspectRaio(File file) throws IOException;
	
	Integer getFileHeight(File file) throws IOException;
	
	Integer getFileWidth(File file) throws IOException;
}
