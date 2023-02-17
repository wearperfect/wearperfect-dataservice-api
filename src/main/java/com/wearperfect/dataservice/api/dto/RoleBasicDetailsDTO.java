package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class RoleBasicDetailsDTO {
	Integer id;
	String name;
	String desc;
	Boolean switchable;
	Boolean active;
}
