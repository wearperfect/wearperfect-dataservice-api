package com.wearperfect.dataservice.api.dto;

import java.util.Date;

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

	Date createdOn;

	User lastUpdatedBy;

	Date lastUpdatedOn;
}
