package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class FeatureBasicDetailsDTO {
	Integer id;
	String desc;
	Integer sequenceId;
	Boolean active;
}
