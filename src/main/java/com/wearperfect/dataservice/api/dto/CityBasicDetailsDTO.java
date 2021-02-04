package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class CityBasicDetailsDTO {

	Integer id;

	String name;

	Long stateId;

	Boolean active;
}
