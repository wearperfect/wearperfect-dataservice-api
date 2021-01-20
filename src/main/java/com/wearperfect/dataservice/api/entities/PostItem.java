package com.wearperfect.dataservice.api.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "post_items")
public class PostItem {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "post_id")
	Long postId;

	@Column(name = "sequence_id")
	Integer sequenceId;

	@Column(name = "source_link")
	String sourceLink;

	@Column(name = "content_type")
	String contentType;

	@Column(name = "active")
	Boolean active;

	@Column(name = "created_on")
	Date createdOn;

	@Column(name = "last_updated_on")
	Date lastUpdatedOn;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Post.class)
	@JoinColumn(name = "post_id", referencedColumnName = "id", insertable = false, updatable = false)
	Post postDetails;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "content_type", referencedColumnName = "extension", insertable = false, updatable = false)
	ContentType contentTypeDetails;

}
