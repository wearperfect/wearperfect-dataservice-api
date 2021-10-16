package com.wearperfect.dataservice.api.dto;

import lombok.Data;

@Data
public class PostUserMentionDTO {

	Long id;

	Long postId;

	Long mentionedUserId;

	Long mentionedBy;

	Long createdOn;
}
