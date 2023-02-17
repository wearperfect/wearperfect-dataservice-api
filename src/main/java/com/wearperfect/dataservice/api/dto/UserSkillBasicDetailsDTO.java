package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class UserSkillBasicDetailsDTO {
	Long id;
	Long userId;
	Integer skillId;
	Boolean active;
}
