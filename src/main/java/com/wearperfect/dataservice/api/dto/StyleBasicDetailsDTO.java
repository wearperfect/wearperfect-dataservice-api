package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class StyleBasicDetailsDTO {
	Integer id;
	String name;
	String desc;
	String thumbnail;
	String sourceLink;
	Boolean active;
}
