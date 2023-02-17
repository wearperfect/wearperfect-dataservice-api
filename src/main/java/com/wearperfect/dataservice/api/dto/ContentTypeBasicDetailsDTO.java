package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class ContentTypeBasicDetailsDTO {
	Integer id;
	String name;
	String type;
	String extension;
	Boolean active;
}
