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
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "messages")
public class Message {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "sent_by")
	Long sentBy;

	@Column(name = "sent_to")
	Long sentTo;

	@Column(name = "text")
	String text;

	@Column(name = "post_id")
	Long postId;

	@Column(name = "media_url")
	String mediaUrl;
	
	@Column(name = "media_thumbnail_url")
	String mediaThumbnailUrl;

	@Column(name = "media_type")
	Integer mediaType;

	@Column(name = "created_on")
	Date createdOn;

	@Column(name = "received_on")
	Date receivedOn;

	@Column(name = "seen_on")
	Date seenOn;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "sent_by", referencedColumnName = "id", insertable = false, updatable = false)
	User sentByUserDetails;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "sent_to", referencedColumnName = "id", insertable = false, updatable = false)
	User sentToUserDetails;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Post.class)
	@JoinColumn(name = "post_id", referencedColumnName = "id", insertable = false, updatable = false)
	Post postDetails;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ContentType.class)
	@JoinColumn(name = "media_type", referencedColumnName = "id", insertable = false, updatable = false)
	ContentType mediaTypeDetails;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserContact.class)
	@JoinColumn(name = "sent_by", referencedColumnName = "user_id", insertable = false, updatable = false)
	UserContact userContact;
}
