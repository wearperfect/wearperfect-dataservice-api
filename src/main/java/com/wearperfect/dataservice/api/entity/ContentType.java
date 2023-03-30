package com.wearperfect.dataservice.api.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "content_types")
public class ContentType implements Serializable{

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(name = "name")
	String name;

	@Column(name = "type")
	String type;

	@Column(name = "extension")
	String extension;

	@Column(name = "desc")
	String desc;

	@Column(name = "thumbnail")
	String thumbnail;

	@Column(name = "active")
	Boolean active;

	@Column(name = "created_by")
	Long createdBy;

	@Column(name = "created_on")
	Date createdOn;

	@Column(name = "last_updated_by")
	Long lastUpdatedBy;

	@Column(name = "last_updated_on")
	Date lastUpdatedOn;

}
