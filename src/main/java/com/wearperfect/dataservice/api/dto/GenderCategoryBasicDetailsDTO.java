package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class GenderCategoryBasicDetailsDTO {

	Integer id;

	String name;

	String shortName;

	Boolean active;
}
