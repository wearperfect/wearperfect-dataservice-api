package com.wearperfect.dataservice.api.serviceImpl;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.service.FileService;
import com.wearperfect.dataservice.api.service.UtilService;

@Service
public class FileServiceImpl implements FileService {
	
	
	@Autowired
	UtilService utilService;

	@Override
	public File converMultipartFileToFile(MultipartFile file) {
		File covertedFile = new File(file.getName());

		try {
			@SuppressWarnings("resource")
			// fos should be closed where it will be consumed.
			FileOutputStream fos = new FileOutputStream(covertedFile);
			fos.write(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return covertedFile;
	}

	@Override
	public String getFileExtension(String fileName) {
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
		} else {
			return "";
		}
	}
	
	@Override
	public BufferedImage convertToBufferedImage(Image image)
	{
	    BufferedImage newImage = new BufferedImage(
	        image.getWidth(null), image.getHeight(null),
	        BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = newImage.createGraphics();
	    g.drawImage(image, 0, 0, null);
	    g.dispose();
	    return newImage;
	}
	
	@Override
	public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
	    Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
	    BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
	    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
	    return outputImage;
	}
	
	@Override
	public File resizeImage(File file, String fileName, int targetWidth, int targetHeight) throws IOException {
		BufferedImage originalImage = ImageIO.read(file);
		BufferedImage scaledImage = resizeImage(originalImage, targetWidth, targetHeight);
		File scaledImageFile = new File(fileName);
		if (scaledImageFile.createNewFile()) {
			// File is created.
		}
		ImageIO.write(scaledImage, getFileExtension(fileName), scaledImageFile);
		return scaledImageFile;
	}
	
	@Override
	public BufferedImage resizeImageByPercent(BufferedImage originalImage, double scale) throws IOException {
		int targetWidth = (int)(originalImage.getWidth()*scale);
		int targetHeight = (int)(originalImage.getHeight()*scale);
	    return resizeImage(originalImage, targetWidth, targetHeight);
	}
	
	@Override
	public File resizeImageByPercent(File file, String fileName, double scale) throws IOException {
		BufferedImage originalImage = ImageIO.read(file);
		int targetWidth = (int)(originalImage.getWidth()*scale);
		int targetHeight = (int)(originalImage.getHeight()*scale);
		return resizeImage(file, fileName, targetWidth, targetHeight);
	}

	@Override
	public Float getFileAspectRaio(File file) throws IOException {
		BufferedImage originalImage = ImageIO.read(file);
		Float aspectRatio = utilService.getRatio(originalImage.getWidth(),originalImage.getHeight());
		return aspectRatio;
	}

}
