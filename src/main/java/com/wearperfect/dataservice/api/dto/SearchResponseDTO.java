package com.wearperfect.dataservice.api.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResponseDTO {
	List<UserDTO> users;
	List<HashTagSearchDTO> hashTags;
}
