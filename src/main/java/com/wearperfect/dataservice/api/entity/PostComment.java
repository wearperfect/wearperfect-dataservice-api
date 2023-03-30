package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "post_comments")
public class PostComment {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "post_id")
	Long postId;

	@Column(name = "description")
	String description;

	@Column(name = "commented_by")
	Long commentedBy;

	@Column(name = "commented_on")
	Date commentedOn;

	@Column(name = "last_updated_on")
	Date lastUpdatedOn;
	
	@Column(name = "active")
	Boolean active;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
	@JoinColumn(name = "commented_by", referencedColumnName = "id",insertable = false, updatable = false)
	User commentedByUserDetails;
}
