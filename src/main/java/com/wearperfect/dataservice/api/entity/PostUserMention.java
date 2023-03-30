package com.wearperfect.dataservice.api.entity;

import java.util.Date;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "post_user_mentions")
public class PostUserMention {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "post_id")
	Long postId;

	@Column(name = "user_id")
	Long userId;

	@Column(name = "created_on")
	Date createdOn;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
	@JoinColumn(name = "user_id", referencedColumnName = "id",insertable = false, updatable = false)
	User userDetails;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Post.class)
	@JoinColumn(name = "post_id", referencedColumnName = "id",insertable = false, updatable = false)
	Post postDetails;
}
