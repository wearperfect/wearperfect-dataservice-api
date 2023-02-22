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
@Table(name = "post_saves")
public class PostSave {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name="post_id")
	Long postId;
	
	@Column(name="saved_to")
	Long savedTo;
	
	@Column(name="saved_by")
	Long savedBy;
	
	@Column(name="saved_on")
	Date savedOn;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Post.class)
	@JoinColumn(name="post_id", referencedColumnName = "id", insertable = false, updatable = false)
	Post postDetails;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
	@JoinColumn(name="saved_by", referencedColumnName = "id", insertable = false, updatable = false)
	User savedByUserDetails;
}
