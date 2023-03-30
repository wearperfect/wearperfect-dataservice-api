package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

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
