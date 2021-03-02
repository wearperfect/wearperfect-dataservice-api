package com.wearperfect.dataservice.api.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	@Column(name = "file_name")
	String fileName;
	
	@Column(name = "content_type")
	String contentType;

	@Column(name = "s3_bucket_id")
	Integer s3BucketId;

	@Column(name = "active")
	Boolean active;

	@Column(name = "created_on")
	Date createdOn;

	@Column(name = "last_updated_on")
	Date lastUpdatedOn;
	
	@ManyToOne(optional = false, targetEntity = Post.class)
	@JoinColumn(name = "post_id", referencedColumnName = "id", insertable = false, updatable = false)
	Post postDetails;
	
//	@ManyToOne(optional = false, targetEntity = ContentType.class)
//	@JoinColumn(name = "content_type", referencedColumnName = "extension", insertable = false, updatable = false)
//	ContentType contentTypeDetails;

}
