package com.wearperfect.dataservice.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserWorksResponseDTO {
	Long userId;
	List<WorkDTO> userWorks;
}
