package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class ColorBasicDetailsDTO {
	Integer id;
	String name;
	String code;
	String desc;
	Boolean active;
}
