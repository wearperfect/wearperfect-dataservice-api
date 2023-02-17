package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class RoleDTO {
	Integer id;
	String name;
	String desc;
	Boolean switchable;
	Boolean active;
	Long createdOn;
	Long lastUpdatedOn;
}
