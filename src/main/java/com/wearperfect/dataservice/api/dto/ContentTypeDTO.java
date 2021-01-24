package com.wearperfect.dataservice.api.dto;

import com.wearperfect.dataservice.api.entities.User;

import lombok.Data;

@Data
public class ContentTypeDTO {

	Integer id;

	String name;

	String type;

	String extension;

	String desc;

	String thumbnail;

	Boolean active;

	User createdBy;

	Long createdOn;

	User lastUpdatedBy;

	Long lastUpdatedOn;
}
