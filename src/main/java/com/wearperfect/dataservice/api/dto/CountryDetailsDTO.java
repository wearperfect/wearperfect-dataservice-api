package com.wearperfect.dataservice.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class CountryDetailsDTO {
	Integer id;
	String name;
	String shortName;
	Boolean active;
	List<StateBasicDetailsDTO> states;
}
