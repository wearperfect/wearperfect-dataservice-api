package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "user_styles")
public class UserStyle {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "user_id")
	Long userId;
	
	@Column(name = "style_id")
	Integer styleId;
	
	@Column(name = "created_by")
	Long createdBy;

	@Column(name = "created_on")
	Date createdOn;
	
	@Column(name = "last_updated_by")
	Long lastUpdatedBy;

	@Column(name = "last_updated_on")
	Date lastUpdatedOn;

	@Column(name = "active")
	Boolean active;
}
