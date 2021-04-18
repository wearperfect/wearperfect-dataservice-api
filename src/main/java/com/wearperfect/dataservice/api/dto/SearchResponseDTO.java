package com.wearperfect.dataservice.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class SearchResponseDTO {

	List<UserDTO> users;
}
