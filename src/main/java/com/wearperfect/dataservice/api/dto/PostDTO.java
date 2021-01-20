package com.wearperfect.dataservice.api.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.wearperfect.dataservice.api.entities.User;

import lombok.Data;

@Data
public class PostDTO {

	Long id;
	String title;
	String description;
	Boolean active;
	Long createdBy;
	Date createdOn;
	Long lastUpdatedBy;
	Date lastUpdatedOn;
	List<PostItemDTO> postItems;
}
