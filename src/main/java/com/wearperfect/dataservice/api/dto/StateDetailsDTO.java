package com.wearperfect.dataservice.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class StateDetailsDTO {

	Integer id;

	String name;

	String shortName;

	Long countryId;

	Boolean active;
	
	List<CityBasicDetailsDTO> cities;
}
