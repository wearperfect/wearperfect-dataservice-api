package com.wearperfect.dataservice.api.dto;

import java.util.List;

import lombok.Data; 

@Data
public class RoleDetailsDTO {
	Integer id;
	String name;
	String desc;
	Boolean switchable;
	Boolean active;
	List<RoleFeatureDetailsDTO> roleFeatures;
}
