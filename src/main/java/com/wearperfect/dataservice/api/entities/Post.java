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
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@Column(name = "title")
	String title;

	@Column(name = "desc")
	String desc;

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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by", referencedColumnName = "id", insertable = false, updatable = false)
	User createdByUserDetails;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "last_updated_by", referencedColumnName = "id", insertable = false, updatable = false)
	User lastUpdatedByUserDetails;
	
	@OneToMany(mappedBy = "postDetails", targetEntity = PostItem.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<PostItem> postItems;
	
}
