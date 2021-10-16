package com.wearperfect.dataservice.api.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "posts")
public class Post {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "title")
	String title;

	@Column(name = "description")
	String description;

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

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "created_by", referencedColumnName = "id", insertable = false, updatable = false)
	User createdByUserDetails;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "last_updated_by", referencedColumnName = "id", insertable = false, updatable = false)
	User lastUpdatedByUserDetails;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = PostMedia.class, mappedBy = "postDetails", cascade = CascadeType.ALL, orphanRemoval = true)
	List<PostMedia> postMediaList;

}
