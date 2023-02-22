package com.wearperfect.dataservice.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "post_likes")
public class PostLike {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name="post_id")
	Long postId;
	
	@Column(name="liked_by")
	Long likedBy;
	
	@Column(name="liked_on")
	Date likedOn;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="liked_by", referencedColumnName = "id", insertable = false, updatable = false)
	User likedByUserDetails;
	
}
