package com.wearperfect.dataservice.api.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="post_media_category_tags")
public class PostMediaCategoryTag {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name="post_media_id")
	Long postMediaId;
	
	@Column(name="category_id")
	Integer categoryId;
	
	@Column(name="created_on")
	Date createdOn;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Category.class)
	@JoinColumn(name = "category_id", referencedColumnName = "id",insertable = false, updatable = false)
	Category category;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = PostMedia.class)
	@JoinColumn(name="post_media_id", referencedColumnName = "id", insertable = false, updatable = false)
	PostMedia postMediaDetails;
}
