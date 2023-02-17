package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class RegionBasicDetailsDTO {
	Integer id;
	String name;
	String shortName;
	String desc;
	String thumbnail;
	String sourceLink;
	Boolean active;
}
