package com.wearperfect.dataservice.api.entity;

import java.util.Date;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="post_media_user_tags")
public class PostMediaUserTag {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "post_media_id")
	Long postMediaId;

	@Column(name = "tagged_user_id")
	Long taggedUserId;
	
	@Column(name = "tag_location_x")
	Double tagLocationX;
	
	@Column(name = "tag_location_y")
	Double tagLocationY;

	@Column(name = "created_on")
	Date createdOn;

	@OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
	@JoinColumn(name = "tagged_user_id", referencedColumnName = "id", insertable = false, updatable = false)
	User taggedUserDetails;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = PostMedia.class)
	@JoinColumn(name="post_media_id", referencedColumnName = "id", insertable = false, updatable = false)
	PostMedia postMediaDetails;
}
