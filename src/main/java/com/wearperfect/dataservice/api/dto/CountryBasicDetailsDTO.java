package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class CountryBasicDetailsDTO {
	Integer id;
	String name;
	String shortName;
	Boolean active;
}
