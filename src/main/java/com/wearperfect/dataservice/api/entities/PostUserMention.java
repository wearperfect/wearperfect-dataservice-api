package com.wearperfect.dataservice.api.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

	@Column(name = "mentioned_user_id")
	Long mentionedUserId;

	@Column(name = "mentioned_by")
	Long mentionedBy;

	@Column(name = "created_on")
	Date mentionedOn;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
	@JoinColumn(name = "mentioned_user_id", referencedColumnName = "id",insertable = false, updatable = false)
	User mentionedUserDetails;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = User.class)
	@JoinColumn(name = "mentioned_by", referencedColumnName = "id",insertable = false, updatable = false)
	User mentionedByUserDetails;
}
