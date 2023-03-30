package com.wearperfect.dataservice.api.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "post_media")
public class PostMedia {

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
	
	@Column(name = "height")
	Integer height;
	
	@Column(name = "width")
	Integer width;
	
	@Column(name = "aspect_ratio")
	Float aspectRatio;

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
	
	@OneToMany(targetEntity = PostMediaUserTag.class, mappedBy = "postMediaDetails", orphanRemoval = true, fetch = FetchType.LAZY)
	List<PostMediaUserTag> userTags;
	
//	@ManyToOne(optional = false, targetEntity = ContentType.class)
//	@JoinColumn(name = "content_type", referencedColumnName = "extension", insertable = false, updatable = false)
//	ContentType contentTypeDetails;

}
