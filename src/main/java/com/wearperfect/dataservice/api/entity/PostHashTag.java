package com.wearperfect.dataservice.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

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
