package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class StateBasicDetailsDTO {
	Integer id;
	String name;
	String shortName;
	Long countryId;
	Boolean active;
}
