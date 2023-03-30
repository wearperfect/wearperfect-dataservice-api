package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "post_hash_tags")
public class PostHashTag {

	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name="post_id")
	Long postId;
	
	@Column(name="hash_tag_id")
	Long hashTagId;
	
	@Column(name="created_on")
	Date createdOn;
	
	@Column(name="active")
	Boolean active;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = HashTag.class)
	@JoinColumn(name = "hash_tag_id", referencedColumnName = "id",insertable = false, updatable = false)
	HashTag hashTag;
}
