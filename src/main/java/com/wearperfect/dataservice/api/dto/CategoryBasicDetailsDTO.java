package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class CategoryBasicDetailsDTO {
	Integer id;
	String name;
	String desc;
	Integer sequence;
	Boolean active;
}
