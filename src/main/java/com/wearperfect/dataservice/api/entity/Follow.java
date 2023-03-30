package com.wearperfect.dataservice.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="follows")
public class Follow {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "user_id")
	Long userId;

	@Column(name = "following_by")
	Long followingBy;

	@Column(name = "active")
	Boolean active;

	@Column(name = "created_on")
	Date createdOn;

	@Column(name = "last_updated_on")
	Date lastUpdatedOn;
	
//	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
//	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
//	User userDetails;
//	
//	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
//	@JoinColumn(name = "followed_by", referencedColumnName = "id", insertable = false, updatable = false)
//	User followingByUserDetails;
}
