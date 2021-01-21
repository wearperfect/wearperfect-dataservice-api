package com.wearperfect.dataservice.api.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PostSaveDTO {

	Long id;
	
	Long postId;
	
	Long savedTo;
	
	Long savedBy;
	
	Date savedOn;
}
