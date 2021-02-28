package com.wearperfect.dataservice.api.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	File converMultipartFileToFile(MultipartFile file);
}
