package com.wearperfect.dataservice.api.utils;

import java.net.URISyntaxException;
import java.net.URL;

public class FileReader {

	public static void main(String[] args) throws URISyntaxException {

		URL resource = FileReader.class.getClassLoader().getResource("input.txt");
		  if (resource == null) {
		      throw new IllegalArgumentException("file not found!");
		  } else {
		      System.out.println(resource.toURI());
		  }
	}
}
