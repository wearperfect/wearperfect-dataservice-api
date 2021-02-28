package com.wearperfect.dataservice.api.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.service.FileService;

@Service
public class FileServiceImpl implements FileService {

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

}
