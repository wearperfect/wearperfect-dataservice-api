package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PostSaveDTO {
	Long id;
	Long postId;
	Long savedTo;
	Long savedBy;
	Long savedOn;
}
